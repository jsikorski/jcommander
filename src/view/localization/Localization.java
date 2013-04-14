package view.localization;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;

public class Localization {
	
	private static String[] supportedLanguages;
	private static LocalizationContext context;
	
	public static void init(String bundleName, String[] supportedLanguages) {
		Localization.supportedLanguages = supportedLanguages;
		context = new LocalizationContext(bundleName, new Locale(supportedLanguages[0]));
	}
	
	public static String get(String key) {
		return context.getBundle().getString(key);
	}

	public static String[] getSupportedLanguages() {
		return supportedLanguages;
	}
	
	public static void changeLanguage(String language) {
		if (!Arrays.asList(supportedLanguages).contains(language)) {
			throw new IllegalArgumentException();
		}
		
		context.setLocale(new Locale(language));
	}
	
	public static void addChangeListener(ContextChangeListener listener) {
		context.addContextChangeListener(listener);
	}
	
	public static void setTextFor(
			final Object target, 
			final String key, 
			final String methodName) {
	
		try {
			final Method method = target.getClass().getMethod(methodName, String.class);
			method.invoke(target, new Object[] { get(key) });
			
			addChangeListener(new ContextChangeListener() {
				@Override
				public void contextChanged() {
					try {
						method.invoke(target, new Object[] { get(key) });
					} catch (Exception e) {
						throw new IllegalArgumentException();
					}
				}
			});
		}
		catch (Exception exception) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void setTextFor(final Object target, final String key) {
		setTextFor(target, key, "setText");
	}
}
