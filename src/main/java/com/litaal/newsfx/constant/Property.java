package com.litaal.newsfx.constant;

public class Property {

	public class PoolConfig {
		public static final String CORE_SIZE = "pool.core_size";
		public static final String MAX_SIZE = "pool.max_size";
		public static final String WAIT = "shutdown.wait";
		public static final String DELAY = "shutdown.delay";
	}

	public class DbConn {
		public static final String PROPERTY_DRIVER = "db_driver";
		public static final String PROPERTY_NEWSDB_URL = "db_news_url";
		public static final String PROPERTY_NEWSDB_USERNAME = "db_news_username";
		public static final String PROPERTY_NEWSDB_PASSWORD = "db_news_password";

		public static final String PROPERTY_USERDB_URL = "db_user_url";
		public static final String PROPERTY_USERDB_USERNAME = "db_user_username";
		public static final String PROPERTY_USERDB_PASSWORD = "db_user_password";
	}

	public class DbName {
		public static final String NEWS_DB = "news_db";
		public static final String USER_DB = "user_db";
	}

	public class MailConfig {
		public static final String ADMIN_USERNAME = "admin.username";
		public static final String ADMIN_PASSWORD = "admin.password";
		public static final String NEW_USER_SUBJECT = "mail.subject.newuser";
		public static final String RESET_PASSW_SUBJECT = "mail.subject.reset";
		public static final String SMTP_HOST = "mail.smtp.host";
		public static final String SMTP_PORT = "mail.smtp.port";
		public static final String SMTP_SSL_ENABLE = "mail.smtp.ssl.enable";
		public static final String SMTP_AUTH_ENABLE = "mail.smtp.auth";
	}
}
