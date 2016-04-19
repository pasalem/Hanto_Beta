/*******************************************************************************
 * This file was developed by Alec Benson and Peter Salem for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 ******************************************/

package student_abenson_pasalem.gamma;

import common.*;
import static common.HantoGameID.GAMMA_HANTO;

import hanto.student_abenson_pasalem.common.BaseHantoGame;
import hanto.student_abenson_pasalem.common.HantoPieceImpl;
import hanto.student_abenson_pasalem.common.PieceFactory.HantoPieceFactory;
import hanto.student_abenson_pasalem.common.RuleValidator.AdjacentOpposingPieceValidator;
import hanto.student_abenson_pasalem.common.RuleValidator.IRuleValidator;
import hanto.student_abenson_pasalem.common.RuleValidator.MovingValidPieceValidator;
import hanto.student_abenson_pasalem.common.RuleValidator.GameRuleSets.PreTurnValidator;
import hanto.student_abenson_pasalem.comon.PlayerState.HantoPlayerStateFactory;

/**
 * Gamma Hanto Game.
 * @author Peter
 *
 */
public class GammaHantoGame extends BaseHantoGame implements HantoGame {
	public GammaHantoGame(HantoPlayerColor movesFirst){
		super(movesFirst);
		maxTurns = 40;
		HantoGameID version = GAMMA_HANTO;
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
		HantoPieceImpl piece;
		IRuleValidator preturnValidator = new PreTurnValidator();
		preturnValidator.validate(this, pieceType, from, to);
		
		//Moving a piece
		if(from != null){
			IRuleValidator checkMovingValidPiece = new MovingValidPieceValidator();
			checkMovingValidPiece.validate(this, pieceType, from, to);
			piece = (HantoPieceImpl) board.getPieceAt(from);
			checkPieceCanMove(piece, from, to);
			board.movePiece(from, to);
		//Placing a piece
		} else{
			IRuleValidator opposingAdjacentValidator = new AdjacentOpposingPieceValidator();
			opposingAdjacentValidator.validate(this, pieceType, from, to);
			piece = HantoPieceFactory.createPiece(currentPlayer, pieceType);
			board.placePiece(piece, to);
			currentPlayerState.getPieceFromInventory(pieceType);
		}
		updateButterflyIfMoved(pieceType, to);
		switchTurn();
		return gameState();
	}
}
