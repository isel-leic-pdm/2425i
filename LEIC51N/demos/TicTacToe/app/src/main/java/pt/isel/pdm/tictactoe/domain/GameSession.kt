package pt.isel.pdm.tictactoe.domain

enum class GameState(val value: Int) {
    Running(0),
    Draw(1),
    Win(2),
    Lose(3),
}


data class GameSession(
    val gameId: String,
    val myName: String,
    val otherPlayerName: String,
    val isMyTurn: Boolean,
    val board: List<Cell>,
    val gameState: GameState,
    val isPlayer1: Boolean
) {
    companion object {
        val none = GameSession("", "", "", false, emptyList(), GameState.Running, false)

        val PLAYER1_CELLSTATE = CellState.X
        val PLAYER2_CELLSTATE = CellState.O

        fun getCellState(isPlayer1: Boolean): CellState {
            if (isPlayer1)
                return PLAYER1_CELLSTATE
            return PLAYER2_CELLSTATE
        }

    }
}


fun GameSession.getMyCellState(): CellState {
    return GameSession.getCellState(this.isPlayer1)
}