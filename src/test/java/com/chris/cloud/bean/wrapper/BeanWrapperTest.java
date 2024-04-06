package com.chris.cloud.bean.wrapper;

import com.chris.cloud.bean.wrapper.beans.Company;
import com.chris.cloud.bean.wrapper.beans.Employee;
import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;
import org.springframework.core.convert.support.DefaultConversionService;

public class BeanWrapperTest {

    /**
     * 输出结果
     * 66666.66
     * Company(name=Pack Company Inc., employee=Employee(name=张三, salary=66666.66, address=[null, null], account={}))
     */
    @Test
    public void test01() {
        BeanWrapper companyBeanWrapper = new BeanWrapperImpl(new Company());
        // 设置属性值
        companyBeanWrapper.setPropertyValue("name", "Pack Company Inc.");
        // 也可以像下面这样
        PropertyValue value = new PropertyValue("name", "Pack Company Inc.");
        companyBeanWrapper.setPropertyValue(value);

        // 创建员工并绑定到Company对象上
        Employee emp = new Employee();
        BeanWrapper empBeanWrapper = new BeanWrapperImpl(emp);
        empBeanWrapper.setPropertyValue("name", "张三");
        empBeanWrapper.setPropertyValue("salary", 66666.66);
        companyBeanWrapper.setPropertyValue("employee", empBeanWrapper.getWrappedInstance());

        // 获取薪水；嵌套属性访问
        Float salary = (Float) companyBeanWrapper.getPropertyValue("employee.salary");
        System.out.println(salary);
        System.out.println(companyBeanWrapper.getWrappedInstance());
    }

    /**
     * 输出结果
     * Employee(name=张三, salary=0.0, address=[新疆, 重庆], account={work=W0001, home=H0001})
     */
    @Test
    public void test02() {

        Employee emp = new Employee();
        BeanWrapper empWrapper = new BeanWrapperImpl(emp);
        empWrapper.setPropertyValue("name", "张三");

        // 通过索引访问
        empWrapper.setPropertyValue("address[0]", "新疆");
        empWrapper.setPropertyValue("address[1]", "重庆");

        // 通过键值访问
        empWrapper.setPropertyValue("account[home]", "H0001");
        empWrapper.setPropertyValue("account[work]", "W0001");

        System.out.println(empWrapper.getWrappedInstance());
    }


    /**
     * 自动类型转换
     */
    @Test
    public void test03() {

        /**
         * 传入salary属性的值是字符串，但在Employee中定义的属性类型是float，BeanWrapper内部自动转换为float
         * 在调用setPropertyValue方法内部会将org.springframework.beans.propertyeditors包中的所有*Editor类进行加载
         * <code>this.defaultEditors.put(Float.class, new CustomNumberEditor(Float.class, true));</code>
         * <p>
         * 输出结果：
         * Employee(name=null, salary=88888.88, address=[null, null], account={})
         */
        BeanWrapper empBeanWrapper = new BeanWrapperImpl(Employee.class);
        empBeanWrapper.setPropertyValue("salary", "88888.88");
        System.out.println(empBeanWrapper.getWrappedInstance());
    }

    /**
     * 自定义类型转换[自定义PropertyEditorSupport]
     * 输出结果：
     * Company(name=腾讯, employee=Employee(name=张三-PropertyEditorSupport, salary=88888.88, address=[新疆, 陕西], account={work=W0001, home=H0001}))
     */
    @Test
    public void test04() {
        BeanWrapperImpl companyBeanWrapper = new BeanWrapperImpl(Company.class);
        companyBeanWrapper.setPropertyValue("name", "腾讯");
        companyBeanWrapper.registerCustomEditor(Employee.class, new Str2EmployeeEditor());
        companyBeanWrapper.setPropertyValue("employee", "张三|88888.88|新疆,陕西|home:H0001,work:W0001");
        System.out.println(companyBeanWrapper.getWrappedInstance());
    }

    /**
     * 自定义类型转换[配置ConversionService转换服务]
     * ConversionService是Spring提供的接口，而PropertyEditorSupport是java自带的。
     * 输出结果：
     * Company(name=null, employee=Employee(name=张三- converter, salary=88888.88, address=[新疆, 陕西], account={work=W0001, home=H0001}))
     */
    @Test
    public void test05() {
        BeanWrapperImpl companyBeanWrapper = new BeanWrapperImpl(Company.class);
        DefaultConversionService defaultConversionService = new DefaultConversionService();
        defaultConversionService.addConverter(new Str2EmployeeConverter());
        companyBeanWrapper.setConversionService(defaultConversionService);
        companyBeanWrapper.setPropertyValue("employee", "张三|88888.88|新疆,陕西|home:H0001,work:W0001");
        System.out.println(companyBeanWrapper.getWrappedInstance());
    }


    /**
     * 在使用PropertyEditor进行类型转换时，如果你要转换到的类型同包中有XxxEditor存在（Xxx，你要转换的类型），则你可以不用注册，
     * BeanWrapper内部会自动的在该包中进行查找，如这里要转换的User对应查找UserEditor类
     * 输出结果：
     * Company(name=null, employee=Employee(name=张三-default-PropertyEditorSupport, salary=88888.88, address=[新疆, 陕西], account={work=W0001, home=H0001}))
     */
    @Test
    public void test06() {
        BeanWrapperImpl companyBeanWrapper = new BeanWrapperImpl(Company.class);
        companyBeanWrapper.setPropertyValue("employee", "张三|88888.88|新疆,陕西|home:H0001,work:W0001");
        System.out.println(companyBeanWrapper.getWrappedInstance());
    }

}
