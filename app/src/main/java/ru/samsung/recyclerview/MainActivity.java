package ru.samsung.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Film> films = new ArrayList<>();

        films.add(new Film("Гарри Поттер и ф.к ",2001,"http://pm1.narvii.com/6659/afa38cc78f6c7d4d9f57b3fd7a6ef22936fa284c_128.jpg"));
        films.add(new Film("Интерстеллар",2014,"https://i01.fotocdn.net/s11/93/gallery_xs/119/2305455708.jpg"));
        films.add(new Film("Голодные игры",2011,"http://www.togomeetings.com/wp-content/uploads/2017/07/fanciful-hunger-games-movie-posters-and-cool-ideas-of-a-look-back-at-the-2-128x128.jpg"));
        films.add(new Film("Звёздные войны эп. 1",1999,"http://www.kcrw.com/news-culture/shows/hollywood-breakdown/star-wars-3d-conversion-plans/@@images/merch_image/thumb"));
        films.add(new Film("Криминальное чтиво",1994,"http://dev.textbase.ru/uploads/cache/128x128/8a/72/9c/33040787.jpg"));
        films.add(new Film("Горбатая гора",2005,"http://mid.images.nitro.to/nitro.to_quotbrokeback_mountainquot_2006_-_soundtrack.cov_3399__440740.jpeg"));
        films.add(new Film("Мистер и миссис Смитт",2005,"https://fsb.zobj.net/crop.php?r=uU3k_no2OXdlvni8J3TtUpNpgSvVmYhOpX2GpblsvJL3Xk8mh7VyL-rkSOk7tu09-9l9k10Y9Ar9ZaJZEmwPLasFR4344V-wITu_mOJp4K2chRYXNIJzB-u-DRqEon0dlKj9wPyVYURKAhCd1S_3YaRSeSPEbs5Bnd2biw"));
        films.add(new Film("Терминатор",1984,"https://i07.fotocdn.net/s28/61/gallery_xs/160/2680463164.jpg"));
        films.add(new Film("Властелин колец",2001,"http://waper.ru/media/200807/15/3/58552-12duic-2ce.jpg"));
        films.add(new Film("Он вам не Димон",2017,"http://ts02.spac.me/tpic/cd083aaaeef843a135dc62a86d3df5a0/165147941.p.129.128.0.jpg?1490681982"));
        films.add(new Film("Форрест Гамп",1994,"http://fc.scelta.it/immagini/5_1_20120413234829.png"));
        films.add(new Film("Джон Уик",2014,"https://community.windy.com/uploads/profile/217296-profileimg.png"));
        films.add(new Film("Загадочная история Б.Б.",2008,"http://file.mobilmusic.ru/ea/c5/e2/356962-128.jpg"));
        films.add(new Film("Геошторм",2017,"http://d1marr3m5x4iac.cloudfront.net/images/block/movies/150468/150468_bj.jpg"));


        rv = findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this)); // устанавливаем разметку для списка.
        rv.setItemAnimator(new DefaultItemAnimator()); //устанавливаем класс, отвечающий за анимации в списке
        rv.setAdapter(new RVAdapter(films,this)); //устанавливаем наш адаптер

    }
}
