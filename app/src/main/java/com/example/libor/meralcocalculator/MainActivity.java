package com.example.libor.meralcocalculator;

import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int typeArray, tabNo, q, rate,setDys,setWks, position, setTab, setHr;
    double KWH, CostPerHour,hrs, monthly, watt, selectHour, z,x;
    EditText wattage,bill;
    ImageView img;
    Button tab1, tab2, tab3, tab4, tab5, tab6;
    TextView text,perHour,perDay,perWeek,perMonth;
    Spinner spinType, spinHours, spinDays, spinWeeks;
    public String hour[] = {"15 mins", "30 mins", "45 mins","1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"
            , "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
    public double hourVal[] = {0.25,0.5,0.75,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
    public int days[] = {1,2,3,4,5,6,7};
    public int weeks[] = {1,2,3,4};
    public String type[][] = {
            //tab1
            {"AIR COOLER / HUMIDIFIER", "BLENDER", "CELLPHONE CHARGER", "CFL (10 watts)", "CFL (18 watts)",
    "CHRISTMAS LIGHT (100 bulbs w/o blinker)", "CHRISTMAS LIGHT (100 bulbs with blinker)", "CLOTHES DRYER (Heater)",
    "CLOTHES DRYER (Motor)", "COFFEE MAKER", "COMPUTER PRINTER", "COMPUTER W/ MONITOR", "FLAT IRON (Standard)",
    "FLAT IRON (Deluxe)", "FLOOR POLISHER (Standard)", "FLOOR POLISHER (Deluxe)", "FLUORESCENT LAMP 21" + " (20 watts)",
            "FLUORESCENT LAMP 48" + " (40 watts)", "HAIR DRYER", "INCANDESCENT BULB (25 watts)", "INCANDESCENT BULB (50 watts)",
    "INCANDESCENT BULB (100 watts)", "RECHARGEABLE LIGHTS / FANS", "SEWING MACHINE", "VACUUM CLEANER", "WASHING MACHINE Automatic (6 kg)"
    , "WASHING MACHINE Automatic (10 kg)", "WASHING MACHINE Twin Tub (6 kg)", "WASHING MACHINE Twin Tub (10 kg)",
    "WATER DISPENSER (Cooling)", "WATER DISPENSER (Heating)", "WATER HEATER", "WATER HEATER (Portable)", "OTHER GENERAL APPLIANCES"}
//tab2
    ,{"INVERTER AIR CONDITIONER (1 HP)", "INVERTER AIR CONDITIONER (1.5 HP)", "AIR CONDITION (0.5 HP)"
            , "AIR CONDITION (0.75 HP)", "AIR CONDITION (1.0 HP)", "AIR CONDITION (1.5 HP)", "AIR CONDITION (2.0 HP)", "AIR CONDITION (2.5 HP)",
            "OTHER CONDITIONERS"}
            //tab3
    ,{"FREEZER CHEST (8 Cu. Ft.)", "FREEZER CHEST (10 Cu. Ft.)",
            "FREEZER CHEST (12 Cu. Ft.)", "FREEZER UPRIGHT (8 Cu. Ft.)", "REFRIGERATOR (6 Cu. Ft.)", "REFRIGERATOR (7 Cu. Ft.)",
            "REFRIGERATOR (8 Cu. Ft.)", "REFRIGERATOR (9 Cu. Ft.)", "REFRIGERATOR (10 Cu. Ft.)", "REFRIGERATOR (11 Cu. Ft.)",
            "REFRIGERATOR (Frost-Free, 7 Cu. Ft.)", "REFRIGERATOR (Frost-Free, 8 Cu. Ft.)", "REFRIGERATOR (Frost-Free, 9 Cu. Ft.)",
            "REFRIGERATOR (Frost-Free, 10 Cu. Ft.)", "REFRIGERATOR (Frost-Free, 11 Cu. Ft.)", "REFRIGERATOR (Frost-Free, 19 Cu. Ft.)",
            "OTHER REFRIGERATORS"}
            //tab4
    ,{"BREAD TOASTER (2 way)", "BREAD TOASTER (4 way)", "FOOD PROCESSOR",
            "FRYER", "GRILLER", "INDUCTION / IH COOKER (single element)", "INDUCTION / IH COOKER (double element)", "MEAT CHOPPER",
            "OSTERIZER / BLENDER", "OVEN, MICROWAVE", "OVEN, MINI", "OVEN, PIZZA (Small)", "OVEN, PIZZA (Big)", "OVEN TOASTER",
            "POPCORN POPPER", "RICE COOKER (1.0 L)", "RICE COOKER (1.8 L)", "RICE COOKER (3.0 L)", "SLOW COOKER (1.0 L)",
            "SLOW COOKER (1.8 L)", "SLOW COOKER (3.0 L)", "STOVE (6"+ " Coil Plate)", "STOVE (8"+ " Coil Plate)", "TURBO BROILER",
            "OTHER COOKING APPLIANCES"}
            //tab5
    ,{"BOX FAN (BIG)","CEILING FAN (2-BLADE)","CEILING FAN (3-BLADE)","CEILING FAN (4-BLADE)","DESK FAN (8"+")","DESK FAN (10"+")",
    "DESK FAN (12"+")","DESK FAN (14"+")","DESK FAN (16"+")","DESK FAN (18"+")","DESK FAN (20"+")","EXHAUST FAN","ORBIT WALL FAN (8"+")",
            "ORBIT WALL FAN (10"+")","ORBIT WALL FAN (12"+")","ORBIT WALL FAN (14"+")","ORBIT WALL FAN (16"+")","ORBIT WALL FAN (18"+")",
            "ORBIT WALL FAN (20"+")","OTHER FANS"}
            //tab6
    ,{"HOME THEATER SYSTEM (5 DVD/CD)","HOME THEATHER SYSTEM (DVD/SACD DIGITAL)","KARAOKE","PLASMA TV (32"+")","PLAYSTATION 1",
            "PLAYSTATION 2","PROJECTION TV (20"+")","PROJECTION TV (43"+")","PROJECTION TV, HDTV MONITOR (55"+")",
            "RADIO CASETTE RECORDER","STEREO (COMPONENT SYSTEM)","STEREO (MINI COMPONENT)","TV SET, COLOR (12"+")",
            "TV SET, COLOR (14"+")","TV SET, COLOR (17"+")","TV SET, COLOR (19"+")","TV SET, COLOR (21"+")","TV SET, COLOR (25"+")",
            "TV SET, COLOR (29"+")","TV SET, COLOR (36"+")","TV SET, COLOR (40"+")","VCD / DVD / MP3 PLAYER","VHS","XBOX",
            "OTHER ENTERTAINMENT APPLIANCES"}};
    public int watts[][] = {{65,300,6,10,18,56,16,1600,250,600,175,225,600,1000,200,360,32,53,320,25,50,100,12,75,800,527,1035,
            277,583,90,550,3000,1600,0}
            ,{850,1090,568,727,944,1252,1913,2664,0}
            ,{160,180,200,150,100,120,130,140,155,170,220,250,280,300,320,800,0}
            ,{800,1500,700,680,1200,1800,3000,700,300,1200,1500,2000,3600,750,1200,450,650,1000,450,650,1000,1500,2200,1000,0}
            ,{90,100,140,160,30,40,50,60,80,120,175,92,30,40,50,60,80,120,175,0}
            ,{150,375,145,380,10,79,52,180,180,50,380,145,65,80,85,110,110,130,145,210,210,45,45,96,0}};
    public double kwh[] = {0.33,5.18,6.64,8.08,10.12,10.50,10.87,11.54,11.80};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView)findViewById(R.id.imageView1);
        tab1 = (Button)findViewById(R.id.tab1);
        tab2 = (Button)findViewById(R.id.tab2);
        tab3 = (Button)findViewById(R.id.tab3);
        tab4 = (Button)findViewById(R.id.tab4);
        tab5 = (Button)findViewById(R.id.tab5);
        tab6 = (Button)findViewById(R.id.tab6);
        text = (TextView)findViewById(R.id.textView);
        perDay = (TextView)findViewById(R.id.CostPerDay);
        perHour = (TextView)findViewById(R.id.CostPerHour);
        perMonth = (TextView)findViewById(R.id.CostPerMonth);
        perWeek = (TextView)findViewById(R.id.CostPerWeek);
        wattage = (EditText)findViewById(R.id.editText);
        bill = (EditText)findViewById(R.id.bill);
        spinHours = (Spinner)findViewById(R.id.spinHours);
        spinDays = (Spinner)findViewById(R.id.spinDays);
        spinWeeks = (Spinner)findViewById(R.id.spinWeeks);
        spinType =(Spinner)findViewById(R.id.spinType);
        tab1.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        setTab = 0;
        position = 0;
        GeneralAppliances();
        Aircon();
        Ref();
        Cooking();
        Fan();
        Entertainment();
        populateSpinner(setTab);
        spinner();
        bill();
        wattage();
        spinHour();
        formula();
        compute();
        hours();
    }
    private void bill()
    {
        bill.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(bill.getText().length() == 0)
                {
                    monthly = 0;
                }
                else
                {
                    monthly = Double.parseDouble(bill.getText().toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                compute();
            }
        });
    }
    private void wattage()
    {
        wattage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(wattage.getText().length() == 0)
                {
                    watt = 0;
                }
                else
                {
                    watt = Double.parseDouble(wattage.getText().toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                compute();
            }
        });
    }
    private void output()
    {
        formula();
        perHour.setText("Cost Per Hour: " + "₱ " + Math.round(CostPerHour*100.0)/100.0);
        perDay.setText("Cost Per Day: " + "₱ " + Math.round((CostPerHour * hourVal[position])*100.0)/100.0);
        perWeek.setText("Cost Per Week: " + "₱ " + Math.round((CostPerHour * (CostPerHour * hourVal[position]))*100.0)/100.0);
        perMonth.setText("Cost Per Month: " + "₱ " + Math.round((CostPerHour * (CostPerHour * (CostPerHour * hourVal[position])))*100.0)/100.0);
    }
    private void spinHour()
    {
        spinHours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void hours()
    {
        spinHours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                for(int x = 0; x < i; i++)
                {
                    hrs = hourVal[x];
                }
                compute();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    private void compute()
    {
        if(monthly <= 108)
        {
            rate = 0;
        }
        else if(monthly <= 338)
        {
            rate = 1;
        }
        else if(monthly <= 573)
        {
            rate = 2;
        }
        else if(monthly <= 1021)
        {
            rate = 3;
        }
        else if(monthly <= 2109)
        {
            rate = 4;
        }
        else if(monthly <= 3271)
        {
            rate = 5;
        }
        else if(monthly <= 4625)
        {
            rate = 6;
        }
        else if(monthly <= 7684)
        {
            rate = 7;
        }
        else
        {
            rate = 8;
        }
        for(int x = 0; x <= rate; x++)
        {
            KWH = kwh[x];
        }
        output();
    }
    private void GeneralAppliances()
    {
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                tab2.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab3.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab4.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab5.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab6.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                text.setText("General Appliances");
                setTab = 0;
                populateSpinner(setTab);
            }
        });
    }
    private void Aircon()
    {
        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab2.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                tab3.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab4.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab5.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab6.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                text.setText("Aircon");
                setTab = 1;
                populateSpinner(setTab);
            }
        });
    }
    private void Ref()
    {
        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab2.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab3.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                tab4.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab5.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab6.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                text.setText("Refrigerator");
                setTab = 2;
                populateSpinner(setTab);
            }
        });
    }
    private void Cooking()
    {
        tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab2.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab3.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab4.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                tab5.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab6.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                text.setText("Cooking");
                setTab = 3;
                populateSpinner(setTab);
            }
        });
    }
    private void Fan()
    {
        tab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab2.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab3.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab4.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab5.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                tab6.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                text.setText("Fan");
                setTab = 4;
                populateSpinner(setTab);
            }
        });
    }
    private void Entertainment()
    {
        tab6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab2.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab3.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab4.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab5.getBackground().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
                tab6.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                text.setText("Entertainment");
                setTab = 5;
                populateSpinner(setTab);
            }
        });
    }
    private void formula()
    {
        switch(setTab)
        {
            case 0:
                CostPerHour = KWH * (watt / 1000);
                break;
            case 1:
                CostPerHour = KWH * ((watt * 0.8 * 0.8) + (watt * 0.2)) / 1000;
                break;
            case 2:
                CostPerHour = (KWH * (watt / 1000) * 14) / 24;
                break;
            case 3:
                CostPerHour = KWH * (watt / 1000);
                break;
            case 4:
                CostPerHour = KWH * (watt / 1000);
                break;
            case 5:
                CostPerHour = KWH * (watt / 1000);
                break;
        }
    }

    private void spinner()
    {
    spinType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            switch(setTab)
            {
                case 0: q = watts[setTab][i];
                    break;
                case 1: q = watts[setTab][i];
                    break;
                case 2: q = watts[setTab][i];
                    break;
                case 3: q = watts[setTab][i];
                    break;
                case 4: q = watts[setTab][i];
                    break;
                case 5: q = watts[setTab][i];
                    break;
            }
            wattage.setText("" + q);
            compute();
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    });
}

    private int populateSpinner(int i){
        switch(i)
        {
            case 0:
                tabNo = i;
                typeArray = type[i].length;
                setHr = position = 10;
                setDys = 6;
                setWks = 3;
                break;
            case 1:
                tabNo = i;
                typeArray = type[i].length;
                setHr = position =  10;
                setDys = 0;
                setWks = 3;
                break;
            case 2:
                tabNo = i;
                typeArray = type[i].length;
                setHr = position =  26;
                setDys = 6;
                setWks = 3;
                break;
            case 3:
                tabNo = i;
                typeArray = type[i].length;
                setHr = position =  0;
                setDys = 6;
                setWks = 3;
                break;
            case 4:
                tabNo = i;
                typeArray = type[i].length;
                setHr = position =  10;
                setDys = 6;
                setWks = 3;
                break;
            case 5:
                tabNo = i;
                typeArray = type[i].length;
                setHr = position =  10;
                setDys = 6;
                setWks = 3;
                break;
        }
        //types
        List<String> listType = new ArrayList<String>();
        for (int x = 0; x< typeArray; x++) {
            listType.add(type[tabNo][x]);
        }
        ArrayAdapter<String> dataAdapterType = new ArrayAdapter<String>(MainActivity.this, R.layout.spinner_type, listType);
        dataAdapterType.setDropDownViewResource(R.layout.spinner_type_dropdown);
        spinType.setAdapter(dataAdapterType);
        spinType.setPrompt("Please choose your appliances");
// hours
        List<String> listHours = new ArrayList<String>();
        for (int x=0;x<hour.length;x++) {
            listHours.add(hour[x]);
        }
        ArrayAdapter<String> dataAdapterHours = new ArrayAdapter<String>(this,
                R.layout.spinner_type, listHours);
        dataAdapterHours.setDropDownViewResource(R.layout.spinner_type_dropdown);
        spinHours.setAdapter(dataAdapterHours);
        spinHours.setPrompt("No. of hours used");
        spinHours.setSelection(setHr);
// days
        List<Integer> listDays = new ArrayList<Integer>();
        for (int x=0;x<days.length;x++) {
            listDays.add(days[x]);
        }
        ArrayAdapter<Integer> dataAdapterDays = new ArrayAdapter<Integer>(this,
                R.layout.spinner_type, listDays);
        dataAdapterDays.setDropDownViewResource(R.layout.spinner_type_dropdown);
        spinDays.setAdapter(dataAdapterDays);
        spinDays.setPrompt("No. of days used");
        spinDays.setSelection(setDys);
// weeks
        List<Integer> listWeeks = new ArrayList<Integer>();
        for (int x=0;x<weeks.length;x++) {
            listWeeks.add(weeks[x]);
        }
        ArrayAdapter<Integer> dataAdapterWeeks = new ArrayAdapter<Integer>(this,
                R.layout.spinner_type, listWeeks);
        dataAdapterWeeks.setDropDownViewResource(R.layout.spinner_type_dropdown);
        spinWeeks.setAdapter(dataAdapterWeeks);
        spinWeeks.setPrompt("No. of weeks used");
        spinWeeks.setSelection(setWks);

        return i;
    }
}