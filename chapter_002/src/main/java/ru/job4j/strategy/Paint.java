package ru.job4j.strategy;

/**Painter of shapes.*/
public class Paint {

	/**Draws shapes.
	*@param shape - shape*/
	public void draw(Shape shape) {
		System.out.print(shape.pic());
	}

}
