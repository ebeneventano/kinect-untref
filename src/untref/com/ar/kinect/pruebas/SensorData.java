package untref.com.ar.kinect.pruebas;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SensorData {

	private byte[] colorFrame;
	private float[] depth;
	private Color[][] matrizColor;
	private float[][] matrizProfundidad;
	private int width = 640;
	private int height = 480;
	private BufferedImage imagen;
	private BufferedImage imagenDepth;

	public SensorData(byte[] colorFrame, float[] depth) {

		this.colorFrame = colorFrame;
		this.depth = depth;

		this.setup();
	}

	private void setup() {

		matrizColor = new Color[this.getWidth()][this.getHeight()];
		matrizProfundidad = new float[this.getWidth()][this.getHeight()];
		imagen = new BufferedImage(this.getWidth(), this.getHeight(),
				BufferedImage.TYPE_3BYTE_BGR);
		imagenDepth = new BufferedImage(this.getWidth(), this.getHeight(),
				BufferedImage.TYPE_3BYTE_BGR);

		for (int i = 0; i < this.getHeight(); i++) {
			for (int j = 0; j < this.getWidth(); j++) {

				int height = this.getWidth() * 4 * i;
				int blue = j * 4 + height;
				int green = j * 4 + 1 + height;
				int red = j * 4 + 2 + height;
				int alpha = j * 4 + 3 + height;

				Color color = new Color(this.colorFrame[red] & 0xFF,
						this.colorFrame[green] & 0xFF,
						this.colorFrame[blue] & 0xFF,
						this.colorFrame[alpha] & 0xFF);

				this.matrizColor[j][i] = color;
				imagen.setRGB(j, i, color.getRGB());
			}
		}

		for (int i = 0; i < 240; i++) {
			for (int j = 0; j < 320; j++) {

				int height = 320 * 3 * i;
				int x = j * 3 + height;
				int y = j * 3 + 1 + height;
				int z = j * 3 + 2 + height;

				// if (j == 320 / 2 && i == 240 / 2) {
				// System.out.print(depth[x]);
				// System.out.print(" - ");
				// System.out.print(depth[y]);
				// System.out.print(" - ");
				// System.out.println(depth[z]);
				// }

				Color color = new Color((int) ((depth[z] / 4.0) * 255), 0, 0);

				this.matrizProfundidad[j][i] = depth[z];
				imagenDepth.setRGB(j, i, color.getRGB());
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

	public BufferedImage getColorImage() {

		return imagen;
	}

	public BufferedImage getDepthImage() {

		return imagenDepth;
	}

}
