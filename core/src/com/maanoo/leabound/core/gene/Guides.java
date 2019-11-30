package com.maanoo.leabound.core.gene;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.board.Board;
import com.maanoo.leabound.core.board.BoardBuilder;


/**
 * A collection of tutorial like boards.
 *
 * @author Akritas Akritidis
 */
public enum Guides {

	Part(new BoardBuilder(
			"*Pick up the [part]part[] and then [leap]leap[] again", ""

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

	)),

	Chest(new BoardBuilder(
			"*Pick up the [part]key[] and open the [chest]chest[]", ""

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

			, "1", "pick-up key" //
			, "2", "locked-chest key parts" //
			, "x", "wall 50"

	)),

	PressurePlate(new BoardBuilder(
			"*Go over the [part]pressure plate[]", ""

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

			, "1", "dispenser l parts" //
			, "x", "wall 50" //

	)),

	Switches(new BoardBuilder(
			"*Flick the two [part]switches[]", ""

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

			, "1", "dispenser r parts" //
			, "2", "and-gate u" //
			, "x", "wall 50" //

	)),

	Pass(new BoardBuilder(
			"*You can pass over the [part]??[] once", ""

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

			, "1", "pick-up key" //
			, "2", "locked-chest key parts" //
			, "x", "wall 50" //

	)),

	Fake(new BoardBuilder(
			"*There is a [part]fake wall[], find it and bump into it", ""

					+ "XXxxxx     xxxx\n"
					+ "Xxxx         xxx\n"
					+ "xx              \n"
					+ "                \n"
					+ "    X     X     \n"
					+ "                \n"
					+ "                \n"
					+ "                \n"
					+ "     X     F    \n"
					+ "                \n"
					+ "x               \n"
					+ "xx              \n"
					+ "xx          xxxx\n"
					+ "XXxxxx      xxxx\n"

			, "x", "wall 50"

	)),

	NotGate(new BoardBuilder(
			"*The small [part]??[] inverts the signal", ""

					+ "XXxxxxx      xxX\n"
					+ "xxxxx         xx\n"
					+ "xxx            x\n"
					+ "x              x\n"
					+ "       -1      x\n"
					+ "       -        \n"
					+ "   S---2        \n"
					+ "       -        \n"
					+ "   S-n-2        \n"
					+ "       -        \n"
					+ "xx   S--        \n"
					+ "xx           xxx\n"
					+ "xxxx        xxxX\n"
					+ "XXxxxxxxxxxxxxXX\n"

			, "1", "dispenser r parts" //
			, "2", "and-gate u" //
			, "n", "not-gate r" //
			, "x", "wall 50" //

	)),

	;

	// === access ===

	public static Guides get(String name) {
		return valueOf(name);
	}

	// === class ===

	private final BoardBuilder board;

	private Guides(BoardBuilder board) {
		this.board = board;
	}

	public Board buildBoard(Player player) {
		return board.build(player);
	}

}
