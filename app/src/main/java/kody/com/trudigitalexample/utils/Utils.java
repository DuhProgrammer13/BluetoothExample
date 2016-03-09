package kody.com.trudigitalexample.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kody on 2/8/16.
 * can be used by kody and people in [kody}]
 */
public class Utils {

    public static File openResourceFile(Context context, int resFile, String tempFileName) throws IOException {
        InputStream in = context.getResources().openRawResource(resFile);

        byte[] b = new byte[in.available()];
        in.read(b);

        FileOutputStream fout = context.openFileOutput(tempFileName, Context.MODE_WORLD_READABLE);

        fout.write(b);
        fout.close();
        in.close();

        return context.getFileStreamPath(tempFileName);
    }
}
