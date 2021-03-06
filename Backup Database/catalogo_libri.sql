PGDMP     	                    z            catalogo_libri    14.1    14.1 0    -           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            .           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            /           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            0           1262    17390    catalogo_libri    DATABASE     j   CREATE DATABASE catalogo_libri WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Italian_Italy.1252';
    DROP DATABASE catalogo_libri;
                postgres    false            ?            1259    38269    autore    TABLE     ?   CREATE TABLE public.autore (
    id bigint NOT NULL,
    cognome character varying(255) NOT NULL,
    nome character varying(255) NOT NULL
);
    DROP TABLE public.autore;
       public         heap    postgres    false            ?            1259    38268    autore_id_seq    SEQUENCE     v   CREATE SEQUENCE public.autore_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.autore_id_seq;
       public          postgres    false    211            1           0    0    autore_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.autore_id_seq OWNED BY public.autore.id;
          public          postgres    false    210            ?            1259    38278 	   categoria    TABLE     d   CREATE TABLE public.categoria (
    id bigint NOT NULL,
    nome character varying(255) NOT NULL
);
    DROP TABLE public.categoria;
       public         heap    postgres    false            ?            1259    38277    categoria_id_seq    SEQUENCE     y   CREATE SEQUENCE public.categoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.categoria_id_seq;
       public          postgres    false    213            2           0    0    categoria_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.categoria_id_seq OWNED BY public.categoria.id;
          public          postgres    false    212            ?            1259    38267    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            ?            1259    38284    libri_autori    TABLE     b   CREATE TABLE public.libri_autori (
    libri_id bigint NOT NULL,
    autore_id bigint NOT NULL
);
     DROP TABLE public.libri_autori;
       public         heap    postgres    false            ?            1259    38289    libri_categoria    TABLE     h   CREATE TABLE public.libri_categoria (
    libri_id bigint NOT NULL,
    categoria_id bigint NOT NULL
);
 #   DROP TABLE public.libri_categoria;
       public         heap    postgres    false            ?            1259    38295    libro    TABLE     ?   CREATE TABLE public.libro (
    id bigint NOT NULL,
    anno_pubb integer NOT NULL,
    prezzo double precision NOT NULL,
    titolo character varying(255) NOT NULL
);
    DROP TABLE public.libro;
       public         heap    postgres    false            ?            1259    38294    libro_id_seq    SEQUENCE     u   CREATE SEQUENCE public.libro_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.libro_id_seq;
       public          postgres    false    217            3           0    0    libro_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.libro_id_seq OWNED BY public.libro.id;
          public          postgres    false    216            ?            1259    38301    role    TABLE     V   CREATE TABLE public.role (
    id bigint NOT NULL,
    name character varying(255)
);
    DROP TABLE public.role;
       public         heap    postgres    false            ?            1259    38306 	   user_role    TABLE     \   CREATE TABLE public.user_role (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);
    DROP TABLE public.user_role;
       public         heap    postgres    false            ?            1259    38311    user_spring    TABLE     ?   CREATE TABLE public.user_spring (
    id bigint NOT NULL,
    email character varying(255),
    is_active boolean NOT NULL,
    password character varying(255),
    user_name character varying(255)
);
    DROP TABLE public.user_spring;
       public         heap    postgres    false            {           2604    38272 	   autore id    DEFAULT     f   ALTER TABLE ONLY public.autore ALTER COLUMN id SET DEFAULT nextval('public.autore_id_seq'::regclass);
 8   ALTER TABLE public.autore ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    211    211            |           2604    38281    categoria id    DEFAULT     l   ALTER TABLE ONLY public.categoria ALTER COLUMN id SET DEFAULT nextval('public.categoria_id_seq'::regclass);
 ;   ALTER TABLE public.categoria ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    213    213            }           2604    38298    libro id    DEFAULT     d   ALTER TABLE ONLY public.libro ALTER COLUMN id SET DEFAULT nextval('public.libro_id_seq'::regclass);
 7   ALTER TABLE public.libro ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216    217            !          0    38269    autore 
   TABLE DATA           3   COPY public.autore (id, cognome, nome) FROM stdin;
    public          postgres    false    211   N4       #          0    38278 	   categoria 
   TABLE DATA           -   COPY public.categoria (id, nome) FROM stdin;
    public          postgres    false    213   ?4       $          0    38284    libri_autori 
   TABLE DATA           ;   COPY public.libri_autori (libri_id, autore_id) FROM stdin;
    public          postgres    false    214   ?4       %          0    38289    libri_categoria 
   TABLE DATA           A   COPY public.libri_categoria (libri_id, categoria_id) FROM stdin;
    public          postgres    false    215   5       '          0    38295    libro 
   TABLE DATA           >   COPY public.libro (id, anno_pubb, prezzo, titolo) FROM stdin;
    public          postgres    false    217   85       (          0    38301    role 
   TABLE DATA           (   COPY public.role (id, name) FROM stdin;
    public          postgres    false    218   ?5       )          0    38306 	   user_role 
   TABLE DATA           5   COPY public.user_role (user_id, role_id) FROM stdin;
    public          postgres    false    219   ?5       *          0    38311    user_spring 
   TABLE DATA           P   COPY public.user_spring (id, email, is_active, password, user_name) FROM stdin;
    public          postgres    false    220   ?5       4           0    0    autore_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.autore_id_seq', 3, true);
          public          postgres    false    210            5           0    0    categoria_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.categoria_id_seq', 3, true);
          public          postgres    false    212            6           0    0    hibernate_sequence    SEQUENCE SET     @   SELECT pg_catalog.setval('public.hibernate_sequence', 4, true);
          public          postgres    false    209            7           0    0    libro_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.libro_id_seq', 3, true);
          public          postgres    false    216                       2606    38276    autore autore_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.autore
    ADD CONSTRAINT autore_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.autore DROP CONSTRAINT autore_pkey;
       public            postgres    false    211            ?           2606    38283    categoria categoria_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.categoria DROP CONSTRAINT categoria_pkey;
       public            postgres    false    213            ?           2606    38288    libri_autori libri_autori_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY public.libri_autori
    ADD CONSTRAINT libri_autori_pkey PRIMARY KEY (libri_id, autore_id);
 H   ALTER TABLE ONLY public.libri_autori DROP CONSTRAINT libri_autori_pkey;
       public            postgres    false    214    214            ?           2606    38293 $   libri_categoria libri_categoria_pkey 
   CONSTRAINT     v   ALTER TABLE ONLY public.libri_categoria
    ADD CONSTRAINT libri_categoria_pkey PRIMARY KEY (libri_id, categoria_id);
 N   ALTER TABLE ONLY public.libri_categoria DROP CONSTRAINT libri_categoria_pkey;
       public            postgres    false    215    215            ?           2606    38300    libro libro_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.libro
    ADD CONSTRAINT libro_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.libro DROP CONSTRAINT libro_pkey;
       public            postgres    false    217            ?           2606    38305    role role_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.role DROP CONSTRAINT role_pkey;
       public            postgres    false    218            ?           2606    38310    user_role user_role_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id);
 B   ALTER TABLE ONLY public.user_role DROP CONSTRAINT user_role_pkey;
       public            postgres    false    219    219            ?           2606    38317    user_spring user_spring_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.user_spring
    ADD CONSTRAINT user_spring_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.user_spring DROP CONSTRAINT user_spring_pkey;
       public            postgres    false    220            ?           2606    38333 +   libri_categoria fk3leanikjseiojj5t5yoa6flon    FK CONSTRAINT     ?   ALTER TABLE ONLY public.libri_categoria
    ADD CONSTRAINT fk3leanikjseiojj5t5yoa6flon FOREIGN KEY (libri_id) REFERENCES public.libro(id);
 U   ALTER TABLE ONLY public.libri_categoria DROP CONSTRAINT fk3leanikjseiojj5t5yoa6flon;
       public          postgres    false    3207    215    217            ?           2606    38338 %   user_role fka68196081fvovjhkek5m97n3y    FK CONSTRAINT     ?   ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fka68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES public.role(id);
 O   ALTER TABLE ONLY public.user_role DROP CONSTRAINT fka68196081fvovjhkek5m97n3y;
       public          postgres    false    3209    219    218            ?           2606    38318 (   libri_autori fkihbnvnb6gi5ihakbto8w1u240    FK CONSTRAINT     ?   ALTER TABLE ONLY public.libri_autori
    ADD CONSTRAINT fkihbnvnb6gi5ihakbto8w1u240 FOREIGN KEY (autore_id) REFERENCES public.autore(id);
 R   ALTER TABLE ONLY public.libri_autori DROP CONSTRAINT fkihbnvnb6gi5ihakbto8w1u240;
       public          postgres    false    214    211    3199            ?           2606    38323 (   libri_autori fkiic0glikwfgnenqsq3ofp7muu    FK CONSTRAINT     ?   ALTER TABLE ONLY public.libri_autori
    ADD CONSTRAINT fkiic0glikwfgnenqsq3ofp7muu FOREIGN KEY (libri_id) REFERENCES public.libro(id);
 R   ALTER TABLE ONLY public.libri_autori DROP CONSTRAINT fkiic0glikwfgnenqsq3ofp7muu;
       public          postgres    false    214    217    3207            ?           2606    38343 %   user_role fkjnbh64dhuo55gh2jd9d21d245    FK CONSTRAINT     ?   ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fkjnbh64dhuo55gh2jd9d21d245 FOREIGN KEY (user_id) REFERENCES public.user_spring(id);
 O   ALTER TABLE ONLY public.user_role DROP CONSTRAINT fkjnbh64dhuo55gh2jd9d21d245;
       public          postgres    false    220    219    3213            ?           2606    38328 +   libri_categoria fkpjdf9tct9tlvcc55usceg8qsw    FK CONSTRAINT     ?   ALTER TABLE ONLY public.libri_categoria
    ADD CONSTRAINT fkpjdf9tct9tlvcc55usceg8qsw FOREIGN KEY (categoria_id) REFERENCES public.categoria(id);
 U   ALTER TABLE ONLY public.libri_categoria DROP CONSTRAINT fkpjdf9tct9tlvcc55usceg8qsw;
       public          postgres    false    3201    215    213            !   F   x?3??M̫????t?I-.N?K)??2?t?/.?O-+????tKM?/?2??/*O???tO?/JO?????? ??      #   2   x?3???M̫??2??K,*J,?,K?2?tK?+I,N?LͫJ?????? ??      $      x?3?4?2?4?2?4?????? A      %      x?3?4?2?4?2?4?????? A      '   P   x?3?4?02z???
E????ř
??ř\F@qNCSN?T?????L???ļ??T.cNCKK?>?"K?=... ?      (   !   x?3???q?wt????2?pB?]??b???? s??      )      x?3?4?2?4?????? ??      *   ?   x?m???   г|?g?Z???,3K6J??)?Af??mu?}?Î??Գ?SBjx??sw\,\?????,V?F?2??Q?G:???4?DM??gR?C(&?m'G?????mm??-[???'????:?j?`?P?ٲ?Ă??T?G!????m?oN ???7h     