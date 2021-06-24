FROM baseimg_1


EXPOSE 8000
WORKDIR /new_chatapp/fundoo/

ENTRYPOINT  python3 manage.py runserver 0.0.0.0:8000

