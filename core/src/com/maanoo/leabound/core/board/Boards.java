package com.maanoo.leabound.core.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.Statistics;

public class Boards {

	public static final HashMap<Integer, BoardBuilder> fixed = new HashMap<Integer, BoardBuilder>();
	static {

		fixed.put(1, new BoardBuilder(
				"!Pick up the [part]part[] and then [leap]leap[] again", ""
						+ "Xxxxx           \n"
						+ "xx          x   \n"
						+ "            Xx  \n"
						+ "   1            \n"
						+ "                \n"
						+ "                \n"
						+ "                \n"
						+ "               X\n"
						+ "               X\n"
						+ "                \n"
						+ "  Xx            \n"
						+ "  x            X\n"
						+ "X             XX\n"
						+ "XX          xxxx\n"

				, "1", "pick-up parts", "x", "wall 50"

		));

		fixed.put(2, new BoardBuilder(
				"!Pick up the [part]key[] and open the [chest]chest[]", ""
						+ "XXXXX      xxXXX\n"
						+ "xx            xX\n"
						+ "                \n"
						+ "   2            \n"
						+ "                \n"
						+ "            1   \n"
						+ "                \n"
						+ "                \n"
						+ "                \n"
						+ "XXX            x\n"
						+ "x              X\n"
						+ "x              X\n"
						+ "XXxx            \n"
						+ "XXXXxxxx        \n"

				, "1", "pick-up key", "2", "locked-chest key parts", "x", "wall 50"

		));

		fixed.put(3, new BoardBuilder(
				"!Go over the [part]pressure plate[]", ""
						+ "XXxxxxx      xxX\n"
						+ "xxxxx         xx\n"
						+ "xxx            x\n"
						+ "x              x\n"
						+ "         1--   x\n"
						+ "           -    \n"
						+ "           -    \n"
						+ "           -    \n"
						+ "           -    \n"
						+ "   P--------    \n"
						+ "xx              \n"
						+ "xx           xxx\n"
						+ "xxxx        xxxX\n"
						+ "XXxxxxxxxxxxxxXX\n"

				, "1", "dispenser l parts", "x", "wall 50"

		));

		fixed.put(4, new BoardBuilder(
				"!Flick the two [part]switches[]", ""
						+ "XXxxxxx      xxX\n"
						+ "xxxxx         xx\n"
						+ "xxx            x\n"
						+ "x              x\n"
						+ "       -1      x\n"
						+ "       -        \n"
						+ "       -        \n"
						+ "   S---2        \n"
						+ "       -        \n"
						+ "       -        \n"
						+ "xx   S--        \n"
						+ "xx           xxx\n"
						+ "xxxx        xxxX\n"
						+ "XXxxxxxxxxxxxxXX\n"

				, "1", "dispenser r parts", "2", "and-gate u", "x", "wall 50"

		));

		fixed.put(5, new BoardBuilder(
				"!You can pass over the [part]??[] once", ""
						+ "XXxxxxXxxxxxxxxx\n"
						+ "x     Xxx    xxx\n"
						+ "x  1  X         \n"
						+ "x     O         \n"
						+ "XXXXXOX         \n"
						+ "xxxx        xxxx\n"
						+ "         XXXXXXX\n"
						+ "         O     x\n"
						+ "         X  2  x\n"
						+ "         XXXXXXX\n"
						+ "x          xxxxx\n"
						+ "Xx              \n"
						+ "Xxx             \n"
						+ "XXXx            \n"

				, "1", "pick-up key", "2", "locked-chest key parts", "x", "wall 50"

		));

	}

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

	public static Board get(Player player) {

		return builders.get(new Random().nextInt(builders.size())).build(player);
	}

	public static Board generateFirstBoard(final Player player) {

		if (Statistics.runs == 0) {

			return new BoardBuilder("Menu", 14, 10, ""

					+ "              \n"
					+ "              \n"
					+ "              \n"
					+ "           S  \n"
					+ "           -  \n"
					+ "           -  \n"
					+ "  -----P   N  \n"
					+ "  -        -  \n"
					+ "  1        2  \n"
					+ "              \n"

					, "1", "display Move\nV\nArrows_/_WASD"//
					, "2", "display Leap\nV\nSpace"

			).build(player, new Bound(14, 10, player));

		} else {

			return new BoardBuilder("Menu", 14, 10, ""

					+ "              \n"
					+ "  ----  ----  \n"
					+ "  S  N  N  S  \n"
					+ "     3  4     \n"
					+ "              \n"
					+ "  P         S \n"
					+ "  --      P - \n"
					+ "   5      A-- \n"
					+ "          - 6 \n"
					+ "          7   \n"

					, "1", "display Move\nV\nArrows_/_WASD" //
					, "2", "display Leap\nV\nSpace"

					, "3",
					"display Last_Run\n" + Statistics.Run.leaps + "_[leap]leaps[]"
							+ (Statistics.newBest ? "\nNEW_BEST" : ""),
					"4", "display Best_Run\n" + Statistics.bestLeaps + "_[leap]leaps[]"

					, "5", "display " + Statistics.text().replace(" ", "_")

					, "6", "display " + "Reset_all\ndata?", "7", "display " + "$Resetting..."

			).build(player, new Bound(14, 10, player));

		}
	}

}
