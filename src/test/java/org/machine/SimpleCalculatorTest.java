package org.machine;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleCalculatorTest {

    private FrameFixture window;

    @BeforeEach
    void setUp() {

        SimpleCalculator calculator = GuiActionRunner.execute(() -> new SimpleCalculator());
        window = new FrameFixture(calculator);
        window.show();
    }

    @AfterEach
    void tearDown() {
        window.cleanUp();
    }

    @Test
    void testCalculatorInitialState() {

        window.textBox().requireText("");

        getButtonByText("0").requireEnabled();
        getButtonByText("1").requireEnabled();
        getButtonByText("+").requireEnabled();
        getButtonByText("=").requireEnabled();
        getButtonByText("C").requireEnabled();
    }

    @Test
    void testNumberButtons() {

        clickButtonsByText("1", "2", "3");
        window.textBox().requireText("123");


        getButtonByText("C").click();
        clickButtonsByText("4", "5", "6");
        window.textBox().requireText("456");
    }

    @Test
    void testAddition() {

        clickButtonsByText("5", "+", "3", "=");
        window.textBox().requireText("8.0");
    }

    @Test
    void testSubtraction() {

        clickButtonsByText("9", "-", "4", "=");
        window.textBox().requireText("5.0");
    }

    @Test
    void testMultiplication() {

        clickButtonsByText("6", "*", "7", "=");
        window.textBox().requireText("42.0");
    }

    @Test
    void testDivision() {

        clickButtonsByText("8", "/", "2", "=");
        window.textBox().requireText("4.0");
    }

    @Test
    void testDivisionByZero() {

        clickButtonsByText("5", "/", "0", "=");
        window.textBox().requireText("Ошибка");
    }

    @Test
    void testClearButton() {

        clickButtonsByText("1", "2", "3");
        window.textBox().requireText("123");

        getButtonByText("C").click();
        window.textBox().requireText("");


        clickButtonsByText("7", "+", "2", "=");
        window.textBox().requireText("9.0");
    }

    @Test
    void testMultipleOperations() {

        clickButtonsByText("2", "+", "3", "=");
        window.textBox().requireText("5.0");

        clickButtonsByText("*", "4", "=");
        window.textBox().requireText("20.0");
    }

    @Test
    void testLargeNumber() {

        clickButtonsByText("9", "9", "9", "9", "9", "9");
        window.textBox().requireText("999999");

        getButtonByText("C").click();


        clickButtonsByText("1", "0", "0", "0", "*", "1", "0", "0", "0", "=");
        window.textBox().requireText("1000000.0");
    }



    @Test
    void testMultipleZeros() {

        clickButtonsByText("0", "0", "0", "1");
        window.textBox().requireText("0001");

        getButtonByText("C").click();


        clickButtonsByText("0", "0", "0");
        window.textBox().requireText("000");
    }

    @Test
    void testButtonLabels() {

        for (int i = 0; i <= 9; i++) {
            getButtonByText(String.valueOf(i)).requireText(String.valueOf(i));
        }

        getButtonByText("+").requireText("+");
        getButtonByText("-").requireText("-");
        getButtonByText("*").requireText("*");
        getButtonByText("/").requireText("/");
        getButtonByText("=").requireText("=");
        getButtonByText("C").requireText("C");
    }

    @Test
    void testComplexCalculation() {

        clickButtonsByText("1", "0", "+", "5", "="); // 15
        window.textBox().requireText("15.0");

        clickButtonsByText("*", "2", "="); // 30
        window.textBox().requireText("30.0");

        clickButtonsByText("/", "3", "="); // 10
        window.textBox().requireText("10.0");
    }

    @Test
    void testContinuousCalculations() {
        // Тестируем непрерывные вычисления
        clickButtonsByText("1", "0", "+", "2", "="); // 12
        window.textBox().requireText("12.0");

        clickButtonsByText("+", "3", "="); // 15
        window.textBox().requireText("15.0");

        clickButtonsByText("+", "5", "="); // 20
        window.textBox().requireText("20.0");
    }

    private JButtonFixture getButtonByText(String text) {
        return window.button(new org.assertj.swing.core.GenericTypeMatcher<javax.swing.JButton>(javax.swing.JButton.class) {
            @Override
            protected boolean isMatching(javax.swing.JButton button) {
                return text.equals(button.getText());
            }

            @Override
            public String toString() {
                return "JButton with text '" + text + "'";
            }
        });
    }

    private void clickButtonsByText(String... buttonTexts) {
        for (String text : buttonTexts) {
            getButtonByText(text).click();
        }
    }
}