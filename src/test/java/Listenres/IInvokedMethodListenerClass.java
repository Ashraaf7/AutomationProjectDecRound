package Listenres;

import Utilities.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.IOException;

import static DriverManager.DriverManager.getDriver;

public class IInvokedMethodListenerClass implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult result)
    {
        System.out.println(iInvokedMethod.getTestMethod() + " " + result.getStatus());
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult result)
    {
        if(result.getStatus() == ITestResult.FAILURE) {
            try {
                Utilities.takeScreenshot(getDriver(),result.getName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
