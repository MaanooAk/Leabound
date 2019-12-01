package com.maanoo.leabound.core.board.pre;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.Statistics;
import com.maanoo.leabound.core.board.Board;
import com.maanoo.leabound.core.board.BoardBuilder;
import com.maanoo.leabound.core.board.Bound;


/**
 * A collection of all the first screens like boards.
 *
 * @author Akritas Akritidis
 */
public final class IntroBoards {

	private IntroBoards() {
	}

	public static Board get(final Player player) {

//		if (Ra.chance(1)) {
//
//			// cover image
//			return new BoardBuilder("Menu", 14, 10, ""
//
//					+ "              \n"
//					+ "  -1    O  3O \n"
//					+ "  -       O  O\n"
//					+ "  2----P   ---\n"
//					+ "  -       X-XX\n"
//					+ "  -     S---XX\n"
//					+ "  S       XXXX\n"
//
//					, "1", "dispenser r key"
//					, "2", "and-gate u"
//					, "3", "locked-chest key parts"
//					, "4", "pick-up key"
//
//			).build(player, new Bound(14, 10, player));
//
//		}

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
