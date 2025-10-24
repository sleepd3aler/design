package ru.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
      String name = "Ivan Ivanov";
      int age = 33;
      LOG.debug("User info name : {}, age : {}", name, age);
      byte first = 1;
      short second = 2;
      int third = 3;
      long forth = 4L;
      float fifth = 5f;
      double sixth = 6;
      char seventh = 7;
      boolean eighth = true;
      LOG.debug("First : {}; Second : {}, Third : {}, Forth : {}, Fifth : {}, Sixth : {}, Seventh : {}, Eighth : {}",
              first, second, third, forth, fifth, sixth, seventh, eighth);

      try {
        throw  new Exception("Not Supported code");
      } catch (Exception e) {
        LOG.error("Exception in log example", e);
        LOG.error("User : {}", "Ivan");
      }
    }
}
