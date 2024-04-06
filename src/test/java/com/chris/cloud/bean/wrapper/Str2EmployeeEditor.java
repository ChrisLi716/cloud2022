package com.chris.cloud.bean.wrapper;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.chris.cloud.bean.wrapper.beans.Employee;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Str2EmployeeEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StrUtil.isBlank(text)) {
            return;
        }

        Employee emp = new Employee();

        List<String> split = StrUtil.split(text, StringPool.PIPE);
        emp.setName(split.get(0) + "-PropertyEditorSupport");
        emp.setSalary(Float.parseFloat(split.get(1)));

        String addressStr = split.get(2);
        if (StrUtil.isNotBlank(addressStr)) {
            emp.setAddress(addressStr.split(StringPool.COMMA));
        }

        String accountStr = split.get(3);
        if (StrUtil.isNotBlank(split.get(3))) {
            HashMap<String, String> map = MapUtil.newHashMap();
            String[] mapStrArray = accountStr.split(StringPool.COMMA);
            Stream.of(mapStrArray).forEach(mapStr -> {
                if (StrUtil.isBlank(mapStr)) {
                    return;
                }
                String[] array = mapStr.split(StringPool.COLON);
                map.put(array[0], array[1]);
            });
            emp.setAccount(map);
        }
        setValue(emp);
    }


    @Override
    public void setValue(Object employee) {
        super.setValue(employee);
    }
}
