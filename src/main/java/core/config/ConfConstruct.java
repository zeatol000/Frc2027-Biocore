package org.team4153.core.config;

/** The main [[org.team4153.core.config.Conf]] class extracts fields off of
 * this class during static construction. Editing these fields before accessing
 * the main Conf class changes how the config is constructed. For example, this
 * could force the Conf class to load the config from a specific file or string
 * instead of the default src/main/resources/application.conf.
 *
 * Default:
 * - load src/main/resources/application.conf
 */
public class ConfConstruct {
	/** 0 for loading src/main/resources/application.conf */
	public static byte constructionType = 0;
}
