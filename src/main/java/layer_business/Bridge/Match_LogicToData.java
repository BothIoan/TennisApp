package layer_business.Bridge;

import layer_data_access.repo.GenericRepo;
import model.TennisGame;
import model.TennisMatch;
import model.TennisSet;

import java.util.ArrayList;
import java.util.List;

public class Match_LogicToData {
    public static void saveMatch(TennisMatch tennisMatch){
        tennisMatch.setId(GenericRepo.save(tennisMatch));
        tennisMatch.getSets().forEach(x->{
            x.setId(GenericRepo.save(x));
            x.getGames().forEach(y->{
                y.setId(GenericRepo.save(y));
            });
        });

    }

    public static void deleteMatch(TennisMatch tennisMatch){
        tennisMatch.getSets().forEach(x->{
            x.getGames().forEach(GenericRepo::delete);
            GenericRepo.delete(x);
        });
        GenericRepo.delete(tennisMatch);
    }

    public static void saveSet(TennisSet tennisSet){
        GenericRepo.save(tennisSet);
        tennisSet.getGames().forEach(GenericRepo::save);
    }
}
