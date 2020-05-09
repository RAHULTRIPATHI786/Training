package org.devlab.ui.customelistener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class AnnotationTransformerListenerClass implements IAnnotationTransformer {

	@Override
	public void transform(
		      ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
				
		
		annotation.setRetryAnalyzer(retryCustomClass.class);
		
//		if (annotation.getEnabled()) {
//			annotation.setEnabled(true);
//		}
//	 
		
	}
}
