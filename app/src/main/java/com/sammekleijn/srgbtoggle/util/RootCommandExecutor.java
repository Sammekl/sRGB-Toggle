package com.sammekleijn.srgbtoggle.util;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class RootCommandExecutor {

    public static boolean runAsRoot(String cmd) {
        try {
            Process p = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(p.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
            os.flush();
        } catch (IOException io) {
            return false;
        }
        return true;
    }

}
