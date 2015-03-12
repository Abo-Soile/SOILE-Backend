describe("Aggragate data functions with stored data", function() {
  beforeEach(function(){
    var data = 2
    var everySecond = 0;
  
    for (var i = 0; i < 10; i++) {
      SOILE2.bin.storerow("data", data);
      if (everySecond == 1) {
        SOILE2.bin.storerow("test", "daa");
        everySecond = 0;
      }else {
        everySecond = 1
      }
      SOILE2.bin.newrow();
      data = data*2;
    };
  })

  afterEach(function() {
    SOILE2.util.resetData();
  })

  it("counts rows", function() {
    var a = SOILE2.bin.count("data");
    var b = SOILE2.bin.count("test", "daa");
    expect(a).toBe(10);
    expect(b).toBe(5);
  });

  it("average of rows", function() {
    var a = SOILE2.bin.average("data");
    expect(a).toBe(204.6);
  })

  it("median of rows", function() {
    var a = SOILE2.bin.median("data");
    expect(a).toBe(48);
  })

  it("standarddeviation of rows", function() {
    var a = SOILE2.bin.standarddeviation("data");
    expect(a).toBeCloseTo(312.97, 1);
  })

  it("removes outliers of rows", function() {
    var a = SOILE2.bin.outliers("data", 0.5);
    var b = SOILE2.bin.outliers("data", 1);

    expect(a.length).toBe(3);
    expect(b.length).toBe(9);
  })
});

