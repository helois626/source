package com.levadom.net.rktest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PhotoLoadClass {

	public static Bitmap getBitmapFromUrl(URL url) {
	    Bitmap bitmap = null;
	    InputStream in = null;
	    OutputStream out = null;

	    try {
	      in = new BufferedInputStream(url.openStream(), 4 * 1024);

	      final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
	      out = new BufferedOutputStream(dataStream, 4 * 1024);
	      copy(in, out);
	      out.flush();

	      final byte[] data = dataStream.toByteArray();
	      bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
	      // Log.e(LOG_TAG, "bitmap returning something");
	      return bitmap;
	    } catch (IOException e) {
	      // Log.e(LOG_TAG, e.getMessage());
	    } finally {
	      closeStream(in);
	      closeStream(out);
	    }
	    // Log.e(LOG_TAG, "bitmap returning null");
	    return null;
	  }



	  private static void copy(InputStream in, OutputStream out)
	      throws IOException {
	    byte[] b = new byte[4 * 1024];
	    int read;
	    while ((read = in.read(b)) != -1) {
	      out.write(b, 0, read);
	    }
	  }

	  private static void closeStream(Closeable stream) {
	    if (stream != null) {
	      try {
	        stream.close();
	      } catch (IOException e) {
	        // Log.e(LOG_TAG, e.getMessage());
	      }
	    }
	  }
}
