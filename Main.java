capsule Main {
	design{
		Market me;
		AggressiveAgent aa[200];
		ConservativeAgent ca[200];
		DesperateAgent da[200];
		PatientAgent pa[200];
		wireall(aa, me);
		wireall(ca, me);
		wireall(da, me);
		wireall(pa, me);
	}
	void run(){}
}
