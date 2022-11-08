package com.chris.cloud.condition;

public class PetConditionV2 extends AbstractEnvCondition {
    @Override
    protected String getConditionProperty() {
        return "animal.dog.enable";
    }
}
