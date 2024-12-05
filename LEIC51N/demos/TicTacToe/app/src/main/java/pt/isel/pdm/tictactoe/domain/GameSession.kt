package pt.isel.pdm.tictactoe.domain

enum class GameState(val value: Int) {
    Running(0),
    Draw(1),
    Win(2),
    Lose(3)
}

data class GameSession(
    val gameId: String,
    val myName : String,
    val otherPlayerName: String,
    val isMyTurn: Boolean,
    val board: List<Cell>,
    val gameState: GameState
) {
}