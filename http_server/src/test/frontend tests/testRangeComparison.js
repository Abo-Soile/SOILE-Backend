describe("Range", function() {
  var range = "";

  beforeEach(function() {
    range = SOILE2.bin.range;
  });


  it("selects string from beginning", function() {
    expect(range("abcdefg",3)).toBe("abc");
  });

    it("selects array from beginning", function() {
    expect(range([1,2,3,4,5,6,7,8,9],5).toString()).toBe([1,2,3,4,5].toString());
  });

  it("selects string from beginning", function() {
    expect(range("abcdefg",3, 6)).toBe("def");
  });

  it("selects array from middle", function() {
    expect(range([1,2,3,4,5,6,7,8,9],5,8).toString()).toBe([6,7,8].toString());
  });

  it("returns false if out of range", function() {
    expect(range("abcdefg",10, 12)).toBe(false);
    expect(range("abcdefg",3, 10)).toBe(false);

    expect(range("abcdefg",10)).toBe(false);
  });

  it("returns false if invalid range", function() {
    expect(range("abcdefg",5, 2)).toBe(false);
    expect(range([1,2,3,4,5,6,7,8,9],3, 10)).toBe(false);
  });

  it("start = end is an invalid range", function() {
    expect(range("abcdefg",2, 2)).toBe(false);
    expect(range([1,2,3,4,5,6,7,8,9],3, 3)).toBe(false);
  });

});