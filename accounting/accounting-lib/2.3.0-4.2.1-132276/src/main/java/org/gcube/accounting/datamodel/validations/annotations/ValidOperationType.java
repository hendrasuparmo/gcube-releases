package org.gcube.accounting.datamodel.validations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.gcube.accounting.datamodel.validations.validators.ValidOperationTypeValidator;
import org.gcube.documentstore.records.implementation.FieldDecorator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@FieldDecorator(action=ValidOperationTypeValidator.class) 
public @interface ValidOperationType {

}
