package untref.com.ar.kinect.pruebas;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SensorDataTesting extends SensorData {

	private BufferedImage color;
	private BufferedImage profundidad;

	public SensorDataTesting() {

		try {
			color = ImageIO.read(new File("heat-bikini-orig.jpg"));
			profundidad = ImageIO.read(new File("heat-bikini-alpha.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Color getColorEnPixel(int x, int y) {

		return new Color(color.getRGB(x, y));
	}

	public float getDistancia(int x, int y) {

		return 0;
	}

	public BufferedImage getImagenColor() {

		return color;
	}

	public BufferedImage getImagenProfundidad() {

		return profundidad;
	}

}
