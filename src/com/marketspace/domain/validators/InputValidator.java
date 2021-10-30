package com.marketspace.domain.validators;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class InputValidator {

	public static void SetNumericInput(TextField textField) {
		// force the field to be numeric only
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					textField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

	}

	public static void SetLimitInput(final TextField textField, final int maxLength) {
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> ov, final String oldValue,
					final String newValue) {
				if (textField.getText().length() > maxLength) {
					String s = textField.getText().substring(0, maxLength);
					textField.setText(s);
				}
			}
		});
	}

	public static void SetNumericLimitInput(final TextField textField, final int maxLength) {
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> ov, final String oldValue,
					final String newValue) {
				if (!newValue.matches("\\d*")) {
					textField.setText(newValue.replaceAll("[^\\d]", ""));
				}
				if (textField.getText().length() > maxLength) {
					String s = textField.getText().substring(0, maxLength);
					textField.setText(s);
				}
			}
		});
	}

	public static void SetFloatInput(final TextField textField) {
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> ov, final String oldValue,
					final String newValue) {
				if (!newValue.matches("\\d+(\\.\\d{1,2})?")) {
					textField.setText((newValue.replace(",", ".")).replaceAll("[^\\d+(\\.\\d{1,2})?]", ""));
				}
			}
		});
	}

	public static void SetFloatLimitInput(final TextField textField, final int maxLength) {
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> ov, final String oldValue,
					final String newValue) {

				if (!newValue.matches("\\d+(\\.\\d{1,2})?")) {
					textField.setText((newValue.replace(",", ".")).replaceAll("[^\\d+(\\.\\d{1,2})?]", ""));
				}
				
				if (textField.getText().length() > maxLength) {
					String s = textField.getText().substring(0, maxLength);
					textField.setText(s);
				}
			}
		});
	}
}
