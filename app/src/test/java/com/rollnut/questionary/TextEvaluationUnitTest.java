package com.rollnut.questionary;

import com.rollnut.questionary.models.levelEvaluation.TextEvaluation;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TextEvaluationUnitTest {

    // True Tests

    // region True: Simple Compare

    @Test
    public void Simple_EqualString_True() {

        String givenA = "Hello";
        String givenB = "Hello";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Simple_EqualStringWithWhiteSpace_True() {

        String givenA = "Hello World";
        String givenB = "Hello World";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    // endregion

    // region True: White Space

    @Test
    public void Simple_DifferentWhiteSpace1_True() {

        String givenA = "HelloWorld";
        String givenB = "Hello World";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Simple_DifferentWhiteSpace2_True() {

        String givenA = "HelloWorld";
        String givenB = " Hello  World ";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Simple_DifferentWhiteSpace3_True() {

        String givenA = "HelloWorld";
        String givenB = " Hel lo  Wo  rld ";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    // endregion

    // region True: Lower Upper Case

    @Test
    public void Simple_DifferentLowerUpperCase1_True() {

        String givenA = "HelloWorld";
        String givenB = "helloworld";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Simple_DifferentLowerUpperCase2_True() {

        String givenA = "HelloWorld";
        String givenB = "hELLOWORLd";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Simple_DifferentLowerUpperCase3_True() {

        String givenA = "HelloWorld";
        String givenB = "heLlOWorLD";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    // endregion

    // region True: White Space + Lower Upper Case

    @Test
    public void Simple_DifferentWhiteSpaceAndLowerUpperCase1_True() {

        String givenA = "HelloWorld";
        String givenB = "hello world";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Simple_DifferentWhiteSpaceAndLowerUpperCase2_True() {

        String givenA = "HelloWorld";
        String givenB = "heLlO WorLD";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Simple_DifferentWhiteSpaceAndLowerUpperCase3_True() {

        String givenA = "HelloWorld";
        String givenB = "  heLlO WorLD  ";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Simple_DifferentWhiteSpaceAndLowerUpperCase4_True() {

        String givenA = "HelloWorld";
        String givenB = "he Ll O Wor L D ";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    // endregion

    // region True: Umlaute

    @Test
    public void Simple_Umlaute1_True() {

        String givenA = "hellö";
        String givenB = "helloe";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Simple_Umlaute2_True() {

        String givenA = "hellä";
        String givenB = "hellae";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Simple_Umlaute3_True() {

        String givenA = "hellü";
        String givenB = "hellue";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Simple_Umlaute4_True() {

        String givenA = "hellö wörld";
        String givenB = "helloe woerld";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    // endregion

    // region True: Umlaute

    @Test
    public void Simple_SpecialCharacters1_True() {

        String givenA = "hello   world";
        String givenB = "hello, world";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Simple_SpecialCharacters2_True() {

        String givenA = "hello   world";
        String givenB = "hello; world";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Simple_SpecialCharacters3_True() {

        String givenA = "hello   world";
        String givenB = "hello. world";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Simple_SpecialCharacters4_True() {

        String givenA = "hello   world";
        String givenB = "hello: world";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Simple_SpecialCharacters5_True() {

        String givenA = "hello   world";
        String givenB = "hello? world";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    // endregion

    // region True: Wildcard

    @Test
    public void Wildcard_PreAndPost1_True() {

        String givenA = "*hello*";
        String givenB = "hello";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Wildcard_PreAndPost2_True() {

        String givenA = "*hello*";
        String givenB = " HelLo  ";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Wildcard_PreAndPost3_True() {

        String givenA = "*hello*";
        String givenB = "hello World";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Wildcard_PreAndPost4_True() {

        String givenA = "*hello*";
        String givenB = "my hello";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Wildcard_PreAndPost5_True() {

        String givenA = "*hello*";
        String givenB = "my hello world";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Wildcard_PreAndPost6_True() {

        String givenA = "*hello*";
        String givenB = "myhelloworld";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Wildcard_PreAndPost7_True() {

        String givenA = "*_hello_*";
        String givenB = " hello ";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Wildcard_PreAndPost8_True() {

        String givenA = "*_hello_*";
        String givenB = "my hello world";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    // endregion


    // False Tests

    // region False: Simple Compare

    @Test
    public void Simple_NonEqualString_False() {

        String givenA = "Hello";
        String givenB = "World";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertFalse(result);
    }

    @Test
    public void Simple_NonEqualString2_False() {

        String givenA = "Hello World";
        String givenB = "World Hello";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertFalse(result);
    }

    @Test
    public void Simple_WrongLetters1_False() {

        String givenA = "hello world";
        String givenB = "hllo world";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertFalse(result);
    }

    @Test
    public void Simple_WrongLetters2_False() {

        String givenA = "hello world";
        String givenB = "ehllo world";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertFalse(result);
    }

    @Test
    public void Simple_WrongLetters3_False() {

        String givenA = "hello";
        String givenB = "hell ";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertFalse(result);
    }

    @Test
    public void Simple_WrongLetterse4_False() {

        String givenA = "hello";
        String givenB = "hellö";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertFalse(result);
    }

    @Test
    public void Simple_WrongLetterse5_False() {

        String givenA = "hello";
        String givenB = "helloe";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertFalse(result);
    }

    @Test
    public void Simple_WrongLetterse6_False() {

        String givenA = "hello";
        String givenB = "helol";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertFalse(result);
    }

    // endregion

    // region False: Wildcard

    @Test
    public void Wildcard_PreAndPost1_False() {

        String givenA = "*hello*";
        String givenB = "my ello";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Wildcard_PreAndPost2_False() {

        String givenA = "*_hello_*";
        String givenB = "hello";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    @Test
    public void Wildcard_PreAndPost3_False() {

        String givenA = "*_hello_*";
        String givenB = "myhelloworld";

        boolean result = new TextEvaluation().evaluateString(givenA, givenB);

        assertTrue(result);
    }

    // endregion
}