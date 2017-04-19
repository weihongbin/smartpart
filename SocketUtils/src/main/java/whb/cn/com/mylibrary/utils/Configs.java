package whb.cn.com.mylibrary.utils;

public class Configs {
	


	public static final int recsize = 5120;
	public static final int sendsize = 20480;
	public static final int timeout = 30;
	
	public static final int CILENT_RECSIZE = 512 * 1024;
	public static final int CLIENT_SENDONSIZE = 1024 * 1024;
	public static final int HEARRT = 0;
	public static final String PRE_HEART = "PRE_HEART";
	public static final int LONGIN = 1;
	public static final int REGISTER = 2;
	public static final int SUCEESS = -2;
	public static final int FAIL = -1;

	public static final int CLIENT2LOGINSERVER = 1;
	public static final int LOGINSERVER2CLIENT = 2;
	public static final int LONGIN2DbSERVER = 3;
	public static final int DBSERVER2LOGINSERVER = 4;
	public static final int LONGIN2CLIENT = 5;
	public static final int CLIENT2MASTERSERVER = 6;
	public static final int MASTER2DbSERVER = 7;
	public static final int DBSERVER2MASTERSERVER = 8;
	public static final int MASTER2CLIENT = 9;
	public static final int MASTER2LogSERVER = 10;
	public static final int LogSERVER2MONITOR = 11;
	public static final int MASTER2MONITORSERVER = 12;
	public static final int MAINSERVER2MONITORSERVER = 13;
}
