import java.text.SimpleDateFormat;
import java.util.Date;

    public class GameHistory {
        private String player;
        private String computer;
        private String winner;
        private Date date;

        public GameHistory(String player, String computer, String winner) {
            this.player = player;
            this.computer = computer;
            this.winner = winner;
            this.date = new Date();
        }

        public String toString() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return "Date: " + dateFormat.format(date) +
                    ", Player: " + player +
                    ", Computer: " + computer +
                    ", Winner: " + winner;
        }
    }

