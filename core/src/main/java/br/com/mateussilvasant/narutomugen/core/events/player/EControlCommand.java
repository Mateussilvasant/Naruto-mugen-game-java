package br.com.mateussilvasant.narutomugen.core.events.player;

public enum EControlCommand {

	PLAYER1 {

		@Override
		public String[] getCommands() {
			return new String[] { "RIGHT", "LEFT", "UP", "Q" };
		}

	},

	PLAYER2 {

		@Override
		public String[] getCommands() {
			return new String[] { "L", "J", "I", "U" };
		}

	};

	public abstract String[] getCommands();

}
