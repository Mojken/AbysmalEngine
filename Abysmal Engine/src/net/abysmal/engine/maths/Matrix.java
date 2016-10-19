package net.abysmal.engine.maths;

public class Matrix {

	Dimension size;
	double[] values;

	public Matrix(Dimension size, double[] values) {
		this.size = size;
		if (values == null || values.length != size.getArea()) this.values = new double[size.getArea()];
		else if (values.length == size.getArea()) this.values = values.clone();
	}

	public Matrix add(Matrix m) {
		if (size.getArea() == m.size.getArea()) {
			double[] d = new double[size.getArea()];

			for (int i = 0; i < size.getArea(); i++) {
				d[i] = values[i] + m.values[i];
			}

			return new Matrix(size, d);
		} else return null;
	}

	public Matrix multiply(Matrix m) {

		Dimension size = new Dimension(m.size.getWidth(), this.size.y);
		double[] d = new double[size.getArea()];
		String[] temp = new String[size.getArea()];
		String[] result = new String[size.getArea()];

		for (int i = 0; i < size.getArea(); i++) {

			for (int a = 0; a < m.size.y; a++)
				temp[i] += m.values[a * m.size.getWidth() + (i % size.getWidth())] + " ";

			temp[i] += ", ";

			for (int b = 0; b < this.size.getWidth(); b++)
				temp[i] += this.values[b + (i % size.getHeight() * this.size.getWidth())] + " ";

			String[] half = temp[i].split(", ");
			String[] value1 = half[0].split(" ");
			String[] value2 = half[1].split(" ");

			if (value1.length == value2.length) for (int a = 0; a < value1.length; a++)
				result[i] += value1[a] + "*" + value2[a] + " + ";

			result[i] = result[i].substring(0, result[i].length() - 3);
			d[i] = Integer.parseInt(result[i]);
		}

		return new Matrix(size, d);
	}

	public Matrix createIdentificationMatrix(int size) {
		Dimension s = new Dimension(size, size);
		double[] d = new double[s.getArea()];

		for (int i = 0; i < d.length; i++)
			d[i] = 0;

		for (int i = 0; i < size; i++)
			d[i * size + i] = 1;

		return new Matrix(s, d);
	}

	public Matrix rowAdd(int row, String operation) {
		String[] args = operation.split(" + ");
		int a = Integer.parseInt((args[0].substring(args[0].length() - 1, args[0].length())));
		int b = Integer.parseInt((args[1].substring(args[0].length() - 1, args[1].length())));

		for (int i = 0; i < size.getWidth(); i++) {
			values[i + row * size.getWidth()] = ((args[0].substring(0, 1) == "-" ? -1:1) * values[i + a * size.getWidth()]) + ((args[1].substring(0, 1) == "-" ? -1:1) * values[i + b * size.getWidth()]);
		}

		return new Matrix(size, values);
	}
}