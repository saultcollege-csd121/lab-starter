package tictactoe.game;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
@Test
    void ParsePositionTestFails(){
//    testing that it throws an error, as it should
    assertThrows(ParseException.class, () -> {Position.parse("1a");});
    assertThrows(ParseException.class, () -> {Position.parse("2b");});
    assertThrows(ParseException.class, () -> {Position.parse("3a");});
    assertThrows(ParseException.class, () -> {Position.parse("owa owa");});
    assertThrows(ParseException.class, () -> {Position.parse("on the top right please");});
    assertThrows(ParseException.class, () -> {Position.parse("3333333333");});
    assertThrows(ParseException.class, () -> {Position.parse("first");});
    assertThrows(ParseException.class, () -> {Position.parse("first second");});
    assertThrows(ParseException.class, () -> {Position.parse("the middle one");});
    }

@Test
    void ParsePositionTestPass(){
//    testing that it doesn't throw an error
    try {
        Position.parse("3C");
    } catch (ParseException e) {
        throw new RuntimeException("3C should be valid");
    }

    try {
        Position.parse("1L");
    } catch (ParseException e) {
        throw new RuntimeException("1L should be valid");
    }

    try {
        Position.parse("2R");
    } catch (ParseException e) {
        throw new RuntimeException("2R should be valid");
    }

    try {
        Position.parse("33");
    } catch (ParseException e) {
        throw new RuntimeException("33 should be valid");
    }

    try {
        Position.parse("top right");
    } catch (ParseException e) {
        throw new RuntimeException("'top right' should be valid");
    }

    try {
        Position.parse("3 1");
    } catch (ParseException e) {
        throw new RuntimeException("'3 1' should be valid");
    }

    try {
        Position.parse("bot right");
    } catch (ParseException e) {
        throw new RuntimeException("'bot right' should be valid");
    }

    try {
        Position.parse("tl");
    } catch (ParseException e) {
        throw new RuntimeException("tl should be valid");
    }

}
}