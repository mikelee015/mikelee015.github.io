/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author mac
 */
public class ConsoleIO implements UserIO {

    Scanner scn = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.print(msg);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return scn.nextLine();
    }

    @Override
    public String reqReadString(String prompt) {
        String userInput = null;
        boolean valid = false;
        while(!valid) {
            userInput = readString(prompt);
            if (userInput != null && !userInput.isBlank()) {
                valid = true;
            } 
        }
        return userInput;
    }

    @Override
    public double readDouble(String prompt) {
        double toReturn = Double.NaN;
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);
            try {
                toReturn = Double.parseDouble(userInput);
                valid = true;
            } catch (NumberFormatException ex) {
            }
        }
        return toReturn;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double toReturn = Double.NaN;
        boolean valid = false;
        while (!valid) {
            toReturn = readDouble(prompt);
            valid = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }

    @Override
    public float readFloat(String prompt) {
        float toReturn = Float.NaN;
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);
            try {
                toReturn = Float.parseFloat(userInput);
                valid = true;
            } catch (NumberFormatException ex) {
            }
        }
        return toReturn;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float toReturn = Float.NaN;
        boolean valid = false;
        while (!valid) {
            toReturn = readFloat(prompt);
            valid = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }

    @Override
    public int readInt(String prompt) {
        int toReturn = Integer.MIN_VALUE;
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);
            try {
                toReturn = Integer.parseInt(userInput);
                valid = true;
            } catch (NumberFormatException ex) {
            }
        }
        return toReturn;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int toReturn = Integer.MIN_VALUE;
        boolean valid = false;
        while (!valid) {
            toReturn = readInt(prompt);
            valid = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }

    @Override
    public long readLong(String prompt) {
        long toReturn = Long.MIN_VALUE;
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);
            try {
                toReturn = Long.parseLong(userInput);
                valid = true;
            } catch (NumberFormatException ex) {
            }
        }
        return toReturn;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long toReturn = Integer.MIN_VALUE;
        boolean valid = false;
        while (!valid) {
            toReturn = readLong(prompt);
            valid = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }

    @Override
    public BigDecimal readBd(String prompt) {
        BigDecimal toReturn = new BigDecimal("0");
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);
            try {
                toReturn = new BigDecimal(userInput);
                valid = true;
            } catch (NumberFormatException ex) {
            }
        }
        return toReturn;
    }

    @Override
    public BigDecimal readBd(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal toReturn = new BigDecimal("0");
        boolean valid = false;
        while (!valid) {
            toReturn = readBd(prompt);
            if (toReturn.compareTo(max) <= 0 && min.compareTo(toReturn) <= 0) {
                valid = true;
            }
        }
        return toReturn;
    }

    @Override
    public LocalDate readDate(String prompt) {
        LocalDate toReturn = LocalDate.MIN;
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);

            try {
                toReturn = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                valid = true;
            } catch (DateTimeParseException ex) {
                print("ERROR: Enter a valid order date in format: mm/dd/yyyy\n");
            }
        }
        return toReturn;
    }

    @Override
    public LocalDate readDate(String prompt, LocalDate earliest, LocalDate latest) {
        LocalDate toReturn = LocalDate.MIN;
        boolean valid = false;
        while (!valid) {

            try {
                toReturn = readDate(prompt);
                if (toReturn.isAfter(earliest) && toReturn.isBefore(latest)) {
                    valid = true;
                }
            } catch (DateTimeParseException ex) {
                print("ERROR: Enter a valid order date in format: mm/dd/yyyy\n");
            }
        }
        return toReturn;
    }

}
