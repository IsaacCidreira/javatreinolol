public class CalcularElo {



    static final int  VALUE_DEFAULT_ELO = 1000;
    int bronze = CalcularElo.VALUE_DEFAULT_ELO;
    int prata = CalcularElo.VALUE_DEFAULT_ELO + 200;
    int gold = CalcularElo.VALUE_DEFAULT_ELO + 400;
    int pointWin = 22;
    int pointLose = 15;

    void mmr(User user) {
        int saldoPartidas = StatusUserGame.calcularValorWinLose(user.status.win, user.status.lose);

        user.mmr =  calcularMMR(saldoPartidas);
        user.elo = calcularElo(user.mmr);
    }

    int calcularMMR(int saldoPartidas){

        int mmr = switch (saldoPartidas) {
            case -1,-2 -> (int) Math.round(CalcularElo.VALUE_DEFAULT_ELO - (saldoPartidas * (pointLose * 1.1)));
            case -3,-4 ->  (int) Math.round(CalcularElo.VALUE_DEFAULT_ELO - (saldoPartidas * (pointLose * 1.2)));
            case -5 ->  (int) Math.round(CalcularElo.VALUE_DEFAULT_ELO - ( saldoPartidas * (pointLose * 1.3)));
            case 0,1 ->  CalcularElo.VALUE_DEFAULT_ELO - pointWin;
            case 2,3 ->  (int) Math.round(CalcularElo.VALUE_DEFAULT_ELO + (saldoPartidas * (pointWin * 1.2)));
            case 4,5 -> (int) Math.round( CalcularElo.VALUE_DEFAULT_ELO + ((saldoPartidas * pointWin * 1.3)));
            default -> {
                if (saldoPartidas > 5) {
                    yield (int) Math.round(CalcularElo.VALUE_DEFAULT_ELO + (saldoPartidas * (pointWin * 1.5)));
                } else if (saldoPartidas < -5) {
                    yield (int) Math.round(CalcularElo.VALUE_DEFAULT_ELO - (-saldoPartidas * (pointLose * 1.5)));
                } else {
                    yield CalcularElo.VALUE_DEFAULT_ELO;
                }
            }
        };

        if(mmr < 0){
            mmr = 0;
        }
        return mmr;

    }


    String calcularElo(int mmr) {
        if (mmr >= gold) {
            return "Gold";
        } else if (mmr >= prata) {
            return "Prata";
        } else if (mmr >= bronze) {
            return "Bronze";
        } else if (mmr < bronze) {
            return "Ferro";
        }else {
            return "Sem elo precisa fazer md10";
        }
    }

}
