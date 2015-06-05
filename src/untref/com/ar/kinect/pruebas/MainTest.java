package untref.com.ar.kinect.pruebas;

import java.awt.Color;

public class MainTest{

	public static void main(String[] args) {
//		Kinect kinect = new Kinect();
//		kinect.start(Kinect.DEPTH| Kinect.COLOR |Kinect.SKELETON |Kinect.XYZ|Kinect.PLAYER_INDEX);
//		kinect.setElevationAngle(27);
//		System.out.println(kinect.getColorFrame()[60000]);
//		System.out.println(kinect.getXYZ());
//
//		byte[] values = new byte[] {kinect.getColorFrame()[0], kinect.getColorFrame()[1], kinect.getColorFrame()[2], kinect.getColorFrame()[3]};
//
////		Color color = Color.argb(values[0] & 0xFF, values[1] & 0xFF, values[2] & 0xFF, values[3] & 0xFF);
//		Color color = new Color(values[0] & 0xFF, values[1] & 0xFF, values[2] & 0xFF, values[3] & 0xFF);
//		
//		System.out.println(color + " alpha " + color.getAlpha());
//		System.out.println(color + " RED " + color.getRed());
//		System.out.println(color + " GREEN " + color.getGreen());
//		System.out.println(color + " BLUE " + color.getBlue());
//		System.out.println(color + " RGB " + color.getRGB());
//		int number = kinect.getMaxNumberOfSkeletons();
		
		FormTest form = new FormTest();
		form.setVisible(true);

		
		form.mostrarImagen();
	}

}
