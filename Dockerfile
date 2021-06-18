FROM python:3.6
mkdir /new_chatapp
COPY . /new_chatapp
COPY /new_chatapp/requirements.txt /new_chatapp/requirements.txt


WORKDIR /new_chatapp/fundoo/
RUN   pip3 install -r /new_chatapp/requirements.txt
RUN pip3 install pymysql
EXPOSE 8000
ENTRYPOINT  python3 manage.py runserver 0.0.0.0:8000
~
~
