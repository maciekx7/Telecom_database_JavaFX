package application.Alerts;

public class AbAlert {
    protected static String getMethodName() {
        return new Throwable()
                .getStackTrace()[2]
                .getMethodName();
    }
}
