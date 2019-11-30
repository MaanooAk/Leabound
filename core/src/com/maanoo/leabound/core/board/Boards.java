package com.maanoo.leabound.core.board;

import java.util.ArrayList;


public class Boards {

	private static final ArrayList<BoardBuilder> builders = new ArrayList<BoardBuilder>();
	static {

		builders.add(new BoardBuilder("Empty", ""
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"));
		builders.clear(); // ???

		builders.add(new BoardBuilder("Wires", ""
				+ "XX              \n"
				+ "X               \n"
				+ "  PWWWWk     P  \n"
				+ "      W      WWL\n"
				+ "      AWWS   N W\n"
				+ "      W      W W\n"
				+ "      Wb        \n"
				+ "  2      S S S  \n"
				+ "         W W W  \n"
				+ "  3      WAWAW  \n"
				+ "          W W   \n"
				+ "XXdX      WAW   \n"
				+ "XppX       c    \n"
				+ "XXXX            \n"

				, "k", "dispenser u key", "b", "dispenser d key", "c", "dispenser r key", "2",
				"locked-chest key master-key", "3", "locked-chest master-key parts", "d", "door key", "p",
				"pick-up parts"));

		builders.add(new BoardBuilder("Wires", ""
				+ "X              X\n"
				+ " X            X \n"
				+ "  p          p  \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "        1       \n"
				+ "        -       \n"
				+ "  S-----a----S  \n"
				+ " X            X \n"
				+ "X              X\n"

				, "1", "dispenser u master-key", "p", "pick-up parts"));

		builders.add(new BoardBuilder("Wires", ""
				+ "                \n"
				+ "                \n"
				+ " 1  2  3  4  5  \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ " 6  7  8  9  0  \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"
				+ "                \n"

				, "1", "pick-up key", "2", "pick-up key-a", "3", "pick-up key-b", "4", "pick-up key-c", "5",
				"pick-up master-key"

				, "6", "locked-chest key parts", "7", "locked-chest key-a parts", "8", "door key-b", "9", "door key-c",
				"0", "locked-chest master-key parts"));

	}

}
