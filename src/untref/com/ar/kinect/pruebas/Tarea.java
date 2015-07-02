package untref.com.ar.kinect.pruebas;

import java.util.TimerTask;

public class Tarea extends TimerTask {

	private FormTest form;

	public Tarea(FormTest form) {

		this.form = form;
	}

	@Override
	public void run() {

		form.actualizar();
	}

}
