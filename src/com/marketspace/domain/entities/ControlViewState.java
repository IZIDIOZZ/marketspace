package com.marketspace.domain.entities;

import javafx.scene.control.Control;

public class ControlViewState {
		private Control control;
		private boolean isEnabled;
		private boolean isEditable;
		
		public Control getControl() {
			return control;
		}

		public void setControl(Control control) {
			this.control = control;
		}

		public boolean isEditable() {
			return isEditable;
		}

		public void setEnabled(boolean isEditable) {
			this.isEditable = isEditable;
		}

		public boolean isEnabled() {
			return isEnabled;
		}

		public void setDisabled(boolean isEnabled) {
			this.isEnabled = isEnabled;
		}

		public ControlViewState(Control control, boolean isEnabled, boolean isEditable) {
			super();
			this.control = control;
			this.isEnabled = isEnabled;
			this.isEditable = isEditable;
		}
		
		public ControlViewState(Control control, boolean isEnabled) {
			super();
			this.control = control;
			this.isEnabled = isEnabled;
			this.isEditable = true;
		}
}
