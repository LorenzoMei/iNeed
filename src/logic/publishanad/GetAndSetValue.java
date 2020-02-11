package logic.publishanad;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GetAndSetValue {
	
	public void getBeanSetEntity(Object bean, Object entity) throws IllegalAccessException, InvocationTargetException {
		
		Method[] methodsBean = bean.getClass().getMethods();
		Method[] methodsEntity = entity.getClass().getMethods();
		
		for(int i = 0; i < methodsBean.length; i++) {
			if(methodsBean[i].getName().contains("get")) {
				for(int j = 0; j < methodsEntity.length; j++) {
					if(methodsEntity[j].getName().contains("set" + methodsBean[i].getName().substring(3, 4).toUpperCase() + methodsBean[i].getName().substring(4))) {
						Object value = methodsBean[i].invoke(bean, (Object[]) null);
						methodsEntity[j].invoke(entity, value);
					}
				}
			}
		}
	}
}
