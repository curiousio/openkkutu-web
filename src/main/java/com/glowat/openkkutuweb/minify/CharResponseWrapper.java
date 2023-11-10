package com.glowat.openkkutuweb.minify;

import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.PrintWriter;


public class CharResponseWrapper extends HttpServletResponseWrapper {

  CharResponseWrapper(HttpServletResponseWrapper response) {
    super(response);
  }

  private final CharArrayWriter output = new CharArrayWriter();

  @Override
  public String toString() {
    return output.toString();
  }

  @Override
  public PrintWriter getWriter() {
    return new PrintWriter(output);
  }

}
