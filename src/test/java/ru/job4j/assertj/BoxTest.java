package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 2);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .isNotBlank()
                .startsWith("C")
                .endsWith("e");
    }

    @Test
    void whenNumberOfVerticesIsEight() {
        Box box = new Box(8, 10);
        int numberOfVertices = box.getNumberOfVertices();
        assertThat(numberOfVertices).isEqualTo(8)
                .isGreaterThan(7)
                .isLessThan(9)
                .isEven()
                .isCloseTo(9, Percentage.withPercentage(12d));
    }

    @Test
    void whenNumberOfVerticesIsFour() {
        Box box = new Box(4, 10);
        int numberOfVertices = box.getNumberOfVertices();
        assertThat(numberOfVertices).isLessThan(5)
                .isPositive()
                .isNotNull();
    }

    @Test
    void whenShapeIsUknown() {
        Box box = new Box(3, 23);
        boolean isShapeExist = box.isExist();
        assertThat(isShapeExist).isFalse();
    }

    @Test
    void whenShapeIsKnown() {
        Box box = new Box(0, 2);
        boolean isShapeExist = box.isExist();
        assertThat(isShapeExist).isTrue();
    }

    @Test
    void whenCubeAreaIsTwentyFour() {
        Box box = new Box(8, 2);
        double area = box.getArea();
        assertThat(area).isEqualTo(24)
                .isNotNull()
                .isLessThan(25)
                .isPositive();
    }

    @Test
    void whenSphereAreaIsInfinite() {
        Box box = new Box(0, 2);
        double area = box.getArea();
        assertThat(area).isCloseTo(50, withPrecision(0.5d))
                .isNotNull()
                .isGreaterThan(49d)
                .isPositive();
    }
}