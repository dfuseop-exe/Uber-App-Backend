-- Insert 40 users into app_user table
INSERT INTO app_user (name, email, password) VALUES
                                                 ('Aarav Sharma', 'aarav.sharma@example.com', 'passAarav'),
                                                 ('Vihaan Desai', 'vihaan.desai@example.com', 'passVihaan'),
                                                 ('Saanvi Iyer', 'saanvi.iyer@example.com', 'passSaanvi'),
                                                 ('Aditya Rao', 'aditya.rao@example.com', 'passAditya'),
                                                 ('Ishaan Patil', 'ishaan.patil@example.com', 'passIshaan'),
                                                 ('Mira Singh', 'mira.singh@example.com', 'passMira'),
                                                 ('Riya Kulkarni', 'riya.kulkarni@example.com', 'passRiya'),
                                                 ('Kabir Nair', 'kabir.nair@example.com', 'passKabir'),
                                                 ('Aanya Joshi', 'aanya.joshi@example.com', 'passAanya'),
                                                 ('Aryan Gupta', 'aryan.gupta@example.com', 'passAryan'),
                                                 ('Dev Mehta', 'dev.mehta@example.com', 'passDev'),
                                                 ('Anaya Verma', 'anaya.verma@example.com', 'passAnaya'),
                                                 ('Parth Shinde', 'parth.shinde@example.com', 'passParth'),
                                                 ('Advika Pawar', 'advika.pawar@example.com', 'passAdvika'),
                                                 ('Reyansh Reddy', 'reyansh.reddy@example.com', 'passReyansh'),
                                                 ('Aadhya Ghosh', 'aadhya.ghosh@example.com', 'passAadhya'),
                                                 ('Krishna Bhat', 'krishna.bhat@example.com', 'passKrishna'),
                                                 ('Arjun Shetty', 'arjun.shetty@example.com', 'passArjun'),
                                                 ('Meera Jain', 'meera.jain@example.com', 'passMeera'),
                                                 ('Vivaan Pillai', 'vivaan.pillai@example.com', 'passVivaan'),
                                                 ('Anvi Menon', 'anvi.menon@example.com', 'passAnvi'),
                                                 ('Rudra Chopra', 'rudra.chopra@example.com', 'passRudra'),
                                                 ('Tara Dixit', 'tara.dixit@example.com', 'passTara'),
                                                 ('Yuvaan Das', 'yuvaan.das@example.com', 'passYuvaan'),
                                                 ('Diya Thakur', 'diya.thakur@example.com', 'passDiya'),
                                                 ('Aarush Kapoor', 'aarush.kapoor@example.com', 'passAarush'),
                                                 ('Navya Rathi', 'navya.rathi@example.com', 'passNavya'),
                                                 ('Ira Pandey', 'ira.pandey@example.com', 'passIra'),
                                                 ('Kiaan Sood', 'kiaan.sood@example.com', 'passKiaan'),
                                                 ('Sanya Vohra', 'sanya.vohra@example.com', 'passSanya'),
                                                 ('Ryan Tiwari', 'ryan.tiwari@example.com', 'passRyan'),
                                                 ('Naina Roy', 'naina.roy@example.com', 'passNaina'),
                                                 ('Shaurya Malhotra', 'shaurya.malhotra@example.com', 'passShaurya'),
                                                 ('Myra Chatterjee', 'myra.chatterjee@example.com', 'passMyra'),
                                                 ('Kabir Shah', 'kabir.shah@example.com', 'passKabirShah'),
                                                 ('Aarohi Bhatt', 'aarohi.bhatt@example.com', 'passAarohi'),
                                                 ('Ranveer Soni', 'ranveer.soni@example.com', 'passRanveer'),
                                                 ('Anaisha Gill', 'anaisha.gill@example.com', 'passAnaisha'),
                                                 ('Ayaan Grover', 'ayaan.grover@example.com', 'passAyaan'),
                                                 ('Lavanya Yadav', 'lavanya.yadav@example.com', 'passLavanya');

INSERT INTO user_roles (user_id, roles) VALUES
                                            (1, 'RIDER'),
                                            (2, 'RIDER'), (2, 'DRIVER'),
                                            (3, 'RIDER'), (3, 'DRIVER'),
                                            (4, 'RIDER'), (4, 'DRIVER'),
                                            (5, 'RIDER'), (5, 'DRIVER'),
                                            (6, 'RIDER'), (6, 'DRIVER'),
                                            (7, 'RIDER'), (7, 'DRIVER'),
                                            (8, 'RIDER'), (8, 'DRIVER'),
                                            (9, 'RIDER'), (9, 'DRIVER'),
                                            (10, 'RIDER'), (10, 'DRIVER'),
                                            (11, 'RIDER'), (11, 'DRIVER'),
                                            (12, 'RIDER'), (12, 'DRIVER'),
                                            (13, 'RIDER'), (13, 'DRIVER'),
                                            (14, 'RIDER'), (14, 'DRIVER'),
                                            (15, 'RIDER'), (15, 'DRIVER'),
                                            (16, 'RIDER'), (16, 'DRIVER'),
                                            (17, 'RIDER'), (17, 'DRIVER'),
                                            (18, 'RIDER'), (18, 'DRIVER'),
                                            (19, 'RIDER'), (19, 'DRIVER'),
                                            (20, 'RIDER'), (20, 'DRIVER'),
                                            (21, 'RIDER'), (21, 'DRIVER'),
                                            (22, 'RIDER'), (22, 'DRIVER'),
                                            (23, 'RIDER'), (23, 'DRIVER'),
                                            (24, 'RIDER'), (24, 'DRIVER'),
                                            (25, 'RIDER'), (25, 'DRIVER'),
                                            (26, 'RIDER'), (26, 'DRIVER'),
                                            (27, 'RIDER'), (27, 'DRIVER'),
                                            (28, 'RIDER'), (28, 'DRIVER'),
                                            (29, 'RIDER'), (29, 'DRIVER'),
                                            (30, 'RIDER'), (30, 'DRIVER'),
                                            (31, 'RIDER'), (31, 'DRIVER'),
                                            (32, 'RIDER'), (32, 'DRIVER'),
                                            (33, 'RIDER'), (33, 'DRIVER'),
                                            (34, 'RIDER'), (34, 'DRIVER'),
                                            (35, 'RIDER'), (35, 'DRIVER'),
                                            (36, 'RIDER'), (36, 'DRIVER'),
                                            (37, 'RIDER'), (37, 'DRIVER'),
                                            (38, 'RIDER'), (38, 'DRIVER'),
                                            (39, 'RIDER'), (39, 'DRIVER'),
                                            (40, 'RIDER'), (40, 'DRIVER');

-- Insert ratings for all 40 riders into the rider table
INSERT INTO rider (user_id, rating) VALUES
                                        (1, 4.1),
                                        (2, 4.3),
                                        (3, 4.5),
                                        (4, 4.2),
                                        (5, 4.0),
                                        (6, 4.4),
                                        (7, 4.7),
                                        (8, 4.5),
                                        (9, 3.9),
                                        (10, 4.1),
                                        (11, 4.6),
                                        (12, 4.3),
                                        (13, 4.4),
                                        (14, 4.2),
                                        (15, 4.0),
                                        (16, 4.5),
                                        (17, 4.8),
                                        (18, 4.1),
                                        (19, 3.8),
                                        (20, 4.7),
                                        (21, 4.2),
                                        (22, 4.5),
                                        (23, 4.0),
                                        (24, 4.3),
                                        (25, 4.4),
                                        (26, 4.6),
                                        (27, 4.2),
                                        (28, 4.1),
                                        (29, 4.8),
                                        (30, 4.0),
                                        (31, 4.5),
                                        (32, 4.3),
                                        (33, 4.6),
                                        (34, 4.4),
                                        (35, 4.1),
                                        (36, 4.2),
                                        (37, 4.7),
                                        (38, 4.3),
                                        (39, 4.0),
                                        (40, 4.5);

-- Insert ratings, availability, and location for all 40 drivers into the driver table
INSERT INTO driver (user_id, rating, available, current_Location) VALUES
                                                                      (2, 4.3, true, ST_GeomFromText('POINT(73.8567 18.5204)', 4326)),  -- Pune
                                                                      (3, 4.5, true, ST_GeomFromText('POINT(73.8446 18.5314)', 4326)),  -- Koregaon Park
                                                                      (4, 4.2, true, ST_GeomFromText('POINT(73.9258 18.5089)', 4326)),  -- Hadapsar
                                                                      (5, 4.0, true, ST_GeomFromText('POINT(73.8416 18.4962)', 4326)),  -- Kothrud
                                                                      (6, 4.4, true, ST_GeomFromText('POINT(73.7769 18.5604)', 4326)),  -- Baner
                                                                      (7, 4.7, true, ST_GeomFromText('POINT(73.7680 18.5920)', 4326)),  -- Wakad
                                                                      (8, 4.5, true, ST_GeomFromText('POINT(73.7051 18.5867)', 4326)),  -- Hinjawadi
                                                                      (9, 3.9, true, ST_GeomFromText('POINT(73.8795 18.5475)', 4326)),  -- Aundh
                                                                      (10, 4.1, true, ST_GeomFromText('POINT(73.8183 18.5006)', 4326)),  -- Deccan
                                                                      (11, 4.6, true, ST_GeomFromText('POINT(73.8545 18.5296)', 4326)),  -- Shivaji Nagar
                                                                      (12, 4.3, true, ST_GeomFromText('POINT(73.8616 18.4961)', 4326)),  -- Erandwane
                                                                      (13, 4.4, true, ST_GeomFromText('POINT(73.8509 18.5247)', 4326)),  -- Model Colony
                                                                      (14, 4.2, true, ST_GeomFromText('POINT(73.8798 18.5029)', 4326)),  -- Sadashiv Peth
                                                                      (15, 4.0, true, ST_GeomFromText('POINT(73.7625 18.5208)', 4326)),  -- Bavdhan
                                                                      (16, 4.5, true, ST_GeomFromText('POINT(73.7388 18.5793)', 4326)),  -- Pimpri
                                                                      (17, 4.8, true, ST_GeomFromText('POINT(73.7561 18.5898)', 4326)),  -- Chinchwad
                                                                      (18, 4.1, true, ST_GeomFromText('POINT(73.8740 18.5320)', 4326)),  -- Kalyani Nagar
                                                                      (19, 3.8, true, ST_GeomFromText('POINT(73.8541 18.5103)', 4326)),  -- Swargate
                                                                      (20, 4.7, true, ST_GeomFromText('POINT(73.7390 18.5803)', 4326)),  -- Pimple Saudagar
                                                                      (21, 4.2, true, ST_GeomFromText('POINT(73.8120 18.5039)', 4326)),  -- Parvati
                                                                      (22, 4.5, true, ST_GeomFromText('POINT(73.7898 18.5167)', 4326)),  -- Warje
                                                                      (23, 4.0, true, ST_GeomFromText('POINT(73.8554 18.5209)', 4326)),  -- Shaniwar Peth
                                                                      (24, 4.3, true, ST_GeomFromText('POINT(73.7616 18.5626)', 4326)),  -- Pashan
                                                                      (25, 4.4, true, ST_GeomFromText('POINT(73.8456 18.4963)', 4326)),  -- Karve Nagar
                                                                      (26, 4.6, true, ST_GeomFromText('POINT(73.7690 18.5453)', 4326)),  -- Sus
                                                                      (27, 4.2, true, ST_GeomFromText('POINT(73.8375 18.5228)', 4326)),  -- Salisbury Park
                                                                      (28, 4.1, true, ST_GeomFromText('POINT(73.8498 18.5084)', 4326)),  -- Market Yard
                                                                      (29, 4.8, true, ST_GeomFromText('POINT(73.8973 18.5047)', 4326)),  -- Kharadi
                                                                      (30, 4.0, true, ST_GeomFromText('POINT(73.8414 18.5225)', 4326)),  -- Mukund Nagar
                                                                      (31, 4.5, true, ST_GeomFromText('POINT(73.8940 18.5101)', 4326)),  -- Magarpatta
                                                                      (32, 4.3, true, ST_GeomFromText('POINT(73.7754 18.5107)', 4326)),  -- Sinhagad Road
                                                                      (33, 4.6, true, ST_GeomFromText('POINT(73.8132 18.5476)', 4326)),  -- Dapodi
                                                                      (34, 4.4, true, ST_GeomFromText('POINT(73.8525 18.5316)', 4326)),  -- Bopodi
                                                                      (35, 4.1, true, ST_GeomFromText('POINT(73.9023 18.5195)', 4326)),  -- Mundhwa
                                                                      (36, 4.2, true, ST_GeomFromText('POINT(73.8583 18.5160)', 4326)),  -- Vishrantwadi
                                                                      (37, 4.7, true, ST_GeomFromText('POINT(73.8672 18.4875)', 4326)),  -- Bibwewadi
                                                                      (38, 4.3, true, ST_GeomFromText('POINT(73.8287 18.5018)', 4326)),  -- Sahakar Nagar
                                                                      (39, 4.0, true, ST_GeomFromText('POINT(73.8939 18.5236)', 4326)),  -- Wanowrie
                                                                      (40, 4.5, true, ST_GeomFromText('POINT(73.8074 18.5223)', 4326));  -- Ghorpadi
