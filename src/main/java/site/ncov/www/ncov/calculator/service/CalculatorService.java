package site.ncov.www.ncov.calculator.service;

import site.ncov.www.ncov.common.exception.WebException;

import java.io.FileNotFoundException;

/**
 * @author 王思源
 * @version 0.0.0
 */

public interface CalculatorService {
    Double queryCode() throws FileNotFoundException, WebException;
}
