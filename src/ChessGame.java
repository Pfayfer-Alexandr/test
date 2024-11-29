import java.util.Scanner;

public class ChessGame {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        board.buildBoard();
        Scanner scanner = new Scanner(System.in);
        String currentPlayer = "White";

        System.out.println("Добро пожаловать в игру в шахматы!");

        while (true) {
            board.printBoard();
            System.out.println("Ход игрока: " + currentPlayer);

            if (board.isCheckmate(currentPlayer)) {
                System.out.println("Шах и мат! Победа игрока " + (currentPlayer.equals("White") ? "Черных" : "Белых"));
                break;
            }

            if (board.isStalemate(currentPlayer)) {
                System.out.println("Пат! Ничья");
                break;
            }

            System.out.print("Введите ход (например, 'e2 e4', или 'exit' для выхода): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) break;

            try {
                String[] parts = input.split(" ");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Неверный формат ввода.");
                }

                String from = parts[0];
                String to = parts[1];

                if (from.length() != 2 || to.length() != 2 ||
                        from.charAt(0) < 'a' || from.charAt(0) > 'h' ||
                        from.charAt(1) < '1' || from.charAt(1) > '8' ||
                        to.charAt(0) < 'a' || to.charAt(0) > 'h' ||
                        to.charAt(1) < '1' || to.charAt(1) > '8') {
                    throw new IllegalArgumentException("Неверные координаты. Пожалуйста, введите значение от 'a1' до 'h8'.");
                }

                int line = 8 - Character.getNumericValue(from.charAt(1));
                int column = from.charAt(0) - 'a';
                int toLine = 8 - Character.getNumericValue(to.charAt(1));
                int toColumn = to.charAt(0) - 'a';

                if (!board.isValidMove(line, column, toLine, toColumn, currentPlayer)) {
                    System.out.println("Неверный ход. Пожалуйста, попробуйте еще раз.");
                    continue;
                }

                if (board.moveToPosition(line, column, toLine, toColumn, currentPlayer)) {
                    System.out.println("Ход выполнен.");
                    currentPlayer = (currentPlayer.equals("White") ? "Black" : "White");
                } else {
                    System.out.println("Неверный ход.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Игра окончена.");
    }
}

class ChessBoard {
    private ChessPiece[][] board = new ChessPiece[8][8];

    public void buildBoard() {
        // Инициализация фигур на доске
        board[0][0] = new Rook("Black");
        board[0][1] = new Knight("Black");
        board[0][2] = new Bishop("Black");
        board[0][3] = new Queen("Black");
        board[0][4] = new King("Black");
        board[0][5] = new Bishop("Black");
        board[0][6] = new Knight("Black");
        board[0][7] = new Rook("Black");

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn("Black");
        }

        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn("White");
        }

        board[7][0] = new Rook("White");
        board[7][1] = new Knight("White");
        board[7][2] = new Bishop("White");
        board[7][3] = new Queen("White");
        board[7][4] = new King("White");
        board[7][5] = new Bishop("White");
        board[7][6] = new Knight("White");
        board[7][7] = new Rook("White");
    }

    public void printBoard() {
        System.out.println("  a b c d e f g h");
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board[i][j];
                System.out.print((piece != null ? piece.getSymbol() : ".") + " ");
            }
            System.out.println();
        }
    }

    public boolean isValidMove(int line, int column, int toLine, int toColumn, String currentPlayer) {
        ChessPiece piece = board[line][column];
        return piece != null && piece.getColor().equals(currentPlayer) && piece.canMoveToPosition(this, line, column, toLine, toColumn);
    }

    public boolean moveToPosition(int line, int column, int toLine, int toColumn, String currentPlayer) {
        ChessPiece piece = board[line][column];
        if (piece != null && isValidMove(line, column, toLine, toColumn, currentPlayer)) {
            board[toLine][toColumn] = piece;
            board[line][column] = null;
            return true;
        }
        return false;
    }

    public boolean isCheckmate(String player) {
        return false;
    }

    public boolean isStalemate(String player) {
        return false;
    }

    public ChessPiece getPiece(int line, int column) {
        return board[line][column];
    }
}

abstract class ChessPiece {
    protected String color;

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract String getSymbol();

    public abstract boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn);
}

class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color.equals("White") ? "P" : "p";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn) {
        int direction = color.equals("White") ? -1 : 1;
        if (column == toColumn) {
            if (toLine == line + direction) {
                return board.getPiece(toLine, toColumn) == null;
            }
            if ((line == 6 && color.equals("White") || line == 1 && color.equals("Black")) && toLine == line + 2 * direction) {
                return board.getPiece(line + direction, column) == null && board.getPiece(toLine, toColumn) == null;
            }
        } else if (Math.abs(column - toColumn) == 1 && toLine == line + direction) {
            return board.getPiece(toLine, toColumn) != null &&
                    !board.getPiece(toLine, toColumn).getColor().equals(color);
        }
        return false;
    }
}

class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color.equals("White") ? "R" : "r";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn) {
        if (line == toLine || column == toColumn) {
            int stepRow = line == toLine ? 0 : (toLine - line) / Math.abs(toLine - line);
            int stepCol = column == toColumn ? 0 : (toColumn - column) / Math.abs(toColumn - column);
            for (int i = line + stepRow, j = column + stepCol; i != toLine || j != toColumn; i += stepRow, j += stepCol) {
                if (board.getPiece(i, j) != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}

class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color.equals("White") ? "B" : "b";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn) {
        if (Math.abs(line - toLine) == Math.abs(column - toColumn)) {
            int stepRow = (toLine - line) / Math.abs(toLine - line);
            int stepCol = (toColumn - column) / Math.abs(toColumn - column);
            for (int i = line + stepRow, j = column + stepCol; i != toLine && j != toColumn; i += stepRow, j += stepCol) {
                if (board.getPiece(i, j) != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}

class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color.equals("White") ? "Q" : "q";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn) {
        Rook rook = new Rook(color);
        Bishop bishop = new Bishop(color);

        return rook.canMoveToPosition(board, line, column, toLine, toColumn) ||
                bishop.canMoveToPosition(board, line, column, toLine, toColumn);
    }
}

class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color.equals("White") ? "K" : "k";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn) {
        return (Math.abs(line - toLine) <= 1 && Math.abs(column - toColumn) <= 1) &&
                (line != toLine || column != toColumn); // Может двигаться на одну клетку
    }
}

class Knight extends ChessPiece {
    public Knight(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color.equals("White") ? "N" : "n";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn) {
        return (Math.abs(line - toLine) == 2 && Math.abs(column - toColumn) == 1) ||
                (Math.abs(line - toLine) == 1 && Math.abs(column - toColumn) == 2);
    }
}