package untref.com.ar.kinect.pruebas;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SensorData {

	private byte[] colorFrame;
	private short[] depth;
	private Color[][] matrizColor;
	private float[][] matrizProfundidad;
	private int width;
	private int height;
	private BufferedImage imagenColor;
	private BufferedImage imagenProfundidad;

	public SensorData() {}

	public SensorData(Kinect kinect) {
		if (!kinect.isInitialized()) {
			System.out.println("Falla al inicializar la kinect.");
			System.exit(2);
		}

		this.colorFrame = kinect.getColorFrame();
		this.depth = kinect.getDepthFrame();

		this.width = kinect.getColorWidth();
		this.height = kinect.getColorHeight();

		this.construirMatrizColor();
		this.construirMatrizProfundidad();
	}

	private void construirMatrizColor() {
		matrizColor = new Color[this.getWidth()][this.getHeight()];
		imagenColor = new BufferedImage(this.getWidth(), this.getHeight(),
				BufferedImage.TYPE_3BYTE_BGR);

		for (int i = 0; i < this.getHeight(); i++) {
			for (int j = 0; j < this.getWidth(); j++) {

				int posicionInicial = j * 4;
				
				int height = this.getWidth() * 4 * i;
				int blue = posicionInicial + height;
				int green = posicionInicial + 1 + height;
				int red = posicionInicial + 2 + height;
				int alpha = posicionInicial + 3 + height;

				Color color = construirColor(blue, green, red, alpha);

				this.matrizColor[j][i] = color;
				imagenColor.setRGB(j, i, color.getRGB());
			}
		}
	}

	private Color construirColor(int blue, int green, int red, int alpha) {
		return new Color(this.colorFrame[red] & 0xFF,
				this.colorFrame[green] & 0xFF,
				this.colorFrame[blue] & 0xFF,
				this.colorFrame[alpha] & 0xFF);
	}

	private void construirMatrizProfundidad() {
		matrizProfundidad = new float[this.getWidth()][this.getHeight()];
		imagenProfundidad = new BufferedImage(this.getWidth(),
				this.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

		for (int i = 0; i < 480; i++) {
			for (int j = 0; j < 640; j++) {

				int height = 640 * i;
				int z = j + height;

				float max = 30000;
				float min = 7000;

				Color color;
				if (depth[z] == 0) {
					color = Color.gray;
				} else if (depth[z] > max) {
					color = Color.black;
				} else if (depth[z] < min) {
					color = Color.white;
				} else {
					float hue = (1 / (max - min)) * (depth[z] - min);
					color = new Color(Color.HSBtoRGB(hue, 1.0f, 1.0f));
				}

				this.matrizProfundidad[j][i] = depth[z];
				imagenProfundidad.setRGB(j, i, color.getRGB());
			}
		}
	}

	private int getWidth() {
		return this.width;
	}

	private int getHeight() {
		return this.height;
	}

	public Color getColorEnPixel(int x, int y) {
		return matrizColor[x][y];
	}

	public float getDistancia(int x, int y) {
		return matrizProfundidad[x][y];
	}

	public BufferedImage getImagenColor() {
		return imagenColor;
	}

	public BufferedImage getImagenProfundidad() {
		return imagenProfundidad;
	}
}