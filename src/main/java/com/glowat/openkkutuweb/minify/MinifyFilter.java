package com.glowat.openkkutuweb.minify;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

public class MinifyFilter implements Filter {

  private HtmlCompressor htmlCompressor = new HtmlCompressor();

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
    try {
      CharResponseWrapper warppedResponse = new CharResponseWrapper(
          (HttpServletResponseWrapper) response);
      chain.doFilter(request, warppedResponse);
      String servletResponse = warppedResponse.toString();
      if(!response.isCommitted()) {
        response.getWriter().write(htmlCompressor.compress(servletResponse));
      }
    } catch (IOException | ServletException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void destroy() {
  }

}
