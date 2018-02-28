package ru.samsung.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by aleksej on 14.02.2018.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.FilmsViewHolder> {

    private List<Film> films;
    private Context context;

    public RVAdapter(List<Film> films, Context context) {
        this.films = films;
        this.context = context;
    }

    @Override
    public FilmsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_rv, parent, false); // создаём вьюшку для кажого элемента
        return new FilmsViewHolder(view); //передаём вьюшку в качестве аргумента для холдера
    }

    @Override
    public void onBindViewHolder(final FilmsViewHolder holder, int position) { //тут будет просходить обработка каждого элемента, кога он появится на экране
        final Film film = films.get(position);// получаем элемент для удобства использования

        holder.txtYear.setText(String.valueOf(film.getYear())); // ставим дату для текстового поля с годом
        holder.txtName.setText(film.getName()); //ставим название года

        if (film.getPoster() == null) {// проверяем есть у нас сохранёная картинка, если нет, скачиваем и сохраняем в память

            new Thread(new Runnable() {//новый поток для работы с сетью. Иначе рабоать не будет!
                @Override
                public void run() {
                    try {
                        URL url = new URL(film.getUrl());
                        final Bitmap poster = BitmapFactory.decodeStream(url.openConnection().getInputStream()); // полчаем картинку по ссылке
                        ((Activity) context).runOnUiThread(new Runnable() { // с визуальными элментами можем работать только в главном потоке! Тут нам помогает контект.
                            @Override
                            public void run() {
                                film.setPoster(poster); // сохраяем картинку, чтобы при повторном проистовании не загружать снова
                                holder.imgPoster.setImageBitmap(poster); //отображаем картинку
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        } else {
            holder.imgPoster.setImageBitmap(film.getPoster());//если картинка была загружена, то просто её отображаем.
        }

        holder.cvListener.setRecord(film);// как-то надо понимать с каким фильмом работаем
        holder.btnClickListener.setRecord(film); // как-то надо понимать с фильмом  работаем

    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    //это самый первый класс, который вы должны создать при содании адептера. В нём происходит инциализации всех View-элементов. Ага!
    class FilmsViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtYear;
        ImageView imgPoster;
        Button btnRemove;
        CardView cv;

        //Инициализируем слушатели
        CardViewClickListener cvListener = new CardViewClickListener();
        ButtonRemoveClickListener btnClickListener = new ButtonRemoveClickListener();

        FilmsViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_rv_name);
            txtYear = itemView.findViewById(R.id.txt_rv_year);
            btnRemove = itemView.findViewById(R.id.btn_rv_remove);
            cv = itemView.findViewById(R.id.cv_rv);
            imgPoster = itemView.findViewById(R.id.img_poster);

            //цепляем слушатели
            cv.setOnClickListener(cvListener);
            btnRemove.setOnClickListener(btnClickListener);


        }
    }

    //классы для обработки нажатий. Главное, чтобы они релизовывали интерфейс View.OnClickListener

    class CardViewClickListener implements View.OnClickListener {

        private Film film;

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context,Main2Activity.class);
            intent.putExtra("test",film.getName());
            ((Activity)context).startActivity(intent);
//            Toast.makeText(context, "Я бы открыл новое окно для \"" + film.getName() + "\" , но мне лень", Toast.LENGTH_SHORT).show();
        }

        void setRecord(Film film) {
            this.film = film;
        }
    }

    class ButtonRemoveClickListener implements View.OnClickListener {
        Film film;

        @Override
        public void onClick(View v) {
            int position = films.indexOf(film); // получаем индекс удаляемого элемента
            films.remove(film); // удаляем его из списка
            notifyItemRemoved(position); // метод для удалаении из самого RecyclerView. Именно он отвечает за анимации
        }

        void setRecord(Film film) {
            this.film = film;
        }
    }
}
