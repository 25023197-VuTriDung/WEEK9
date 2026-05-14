package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String studentName = "Mai Anh";


        logger.info("Chuong trinh Logging cua {} bat dau chay...", studentName);

        try {

            int result = 10 / 2;
            logger.info("Ket qua phep tinh la: {}", result);


            logger.info("Dang thu nghiem ghi log loi...");
            int error = 10 / 0;

        } catch (Exception e) {

            logger.error("DA XAY RA LOI: {}", e.getMessage());
        }

        logger.info("Hoan thanh Bai 09!");
    }
}