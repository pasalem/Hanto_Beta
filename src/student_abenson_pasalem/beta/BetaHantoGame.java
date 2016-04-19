/*******************************************************************************
 * This file was developed by Alec Benson and Peter Salem for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 ******************************************/

package student_abenson_pasalem.beta;

import static common.HantoPlayerColor.BLUE;
import static common.MoveResult.BLUE_WINS;
import static common.MoveResult.RED_WINS;

import common.*;
import hanto.student_abenson_pasalem.common.BaseHantoGame;
import hanto.student_abenson_pasalem.common.PieceFactory.HantoPieceFactory;
import hanto.student_abenson_pasalem.common.RuleValidator.IRuleValidator;
import hanto.student_abenson_pasalem.common.RuleValidator.GameRuleSets.PreTurnValidator;
import hanto.student_abenson_pasalem.comon.PlayerState.HantoPlayerStateFactory;

/**
 * Constructor for the beta version of the hanto game
 */
public class BetaHantoGame extends BaseHantoGame implements HantoGame {
	/**
	 * Constructor for BetaHantoGame.
	 * @param startPlayer
	 */
	public BetaHantoGame(HantoPlayerColor movesFirst) {
		super(movesFirst);
		maxTurns = 12;
		canResign = false;
		HantoGameID version = HantoGameID.BETA_HANTO;
		bluePlayerState = HantoPlayerStateFactory.makePlayerState(
				version, HantoPlayerColor.BLUE);
		redPlayerState = HantoPlayerStateFactory.makePlayerState(
				version, HantoPlayerColor.RED);	
		currentPlayerState = movesFirst == HantoPlayerColor.BLUE ? bluePlayerState : redPlayerState;
	}
	
	/*
	 * @see hanto.common.HantoGame#makeMove(hanto.common.HantoPieceType,
	 * hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
	 */
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to)
		throws HantoException {
		
		//Check if there was a resignation move
		checkPlayerResigned(pieceType, from, to);
		
		if(from != null){
			throw new HantoException("Cannot move in beta hanto");
		} else{
			HantoPiece piece = HantoPieceFactory.createPiece(currentPlayer, pieceType);
			IRuleValidator moveValidator = new PreTurnValidator();
			moveValidator.validate(this, pieceType, from, to);
			board.placePiece(piece, to);
			currentPlayerState.getPieceFromInventory(pieceType);
		}
		updateButterflyIfMoved(pieceType, to);
		switchTurn();
		return gameState();
	}
	
}